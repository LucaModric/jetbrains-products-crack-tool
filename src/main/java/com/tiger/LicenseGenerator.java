package com.tiger;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 * 第3步，生成激活码
 *
 */
public class LicenseGenerator {
    public static final String[] DEFAULT_CODES = {
            "II", "PSI", "PS", "AC", "DB", "RM", "WS", "RD", "CL", "PC", "GO", "DS", "DC", "DPN", "DM",
            "PSYMFONYPLUGIN", "PWLANG", "PSWPLUGIN", "PGITTOOLBOX", "PHYBRISCOMMERCE", "PMATERIALUI",
            "PSEQUENCEDIAGRA", "PJETFORCER", "PAEMIDE", "PRNCONSOLE", "PANSIHIGHLIGHT", "PYAOQIANGBPMN",
            "PAEM", "PRAINBOWBRACKET", "PGITSCOPE", "PVLOG", "PCODEMRBASE", "PJDCLEANREAD", "PBRWJV",
            "PDB", "PEXTRAICONS", "PBISJ", "PSCIPIO", "PBISAA", "PZENUML", "PJFORMDESIGNER", "PORCHIDE",
            "PIEDIS", "PCMAKEPLUS", "POPENAPI", "PBETTERHIGHLIGH", "PATOMONEDARK", "PGDOC", "POFFICEFLOOR",
            "PWIFIADB", "PLARAVEL", "PODOO", "PCREVIEW", "PMRINTEGEE", "PSFCC", "PMINBATIS", "PPOJOTOJSONSCH",
            "PRDFANDSPARQL", "PBASHSUPPORTPRO", "PMYBATISLOG", "PSMARTJUMP", "PJAVACODESUGG", "PGOLANGCODESUGG",
            "PRUBYCODESUGG", "PVCS", "PJSCODESUGG", "PPHPCODESUGG", "PSVERILOG", "PSPARQL", "PTOOLSET", "PJSONTOTS",
            "PQMLEDITOR", "PSTRKER", "PELASTICSEARCH", "PVISUALGC", "PPYCODESUGG", "PFLUTTER", "PRESTKIT",
            "PAWSLAMBDADEPLR", "PPUMLSTUDIO", "PCWMP", "PFIREHIGHLIGHT", "PJPASQL", "PGODRUNNER", "PLEDGER",
            "PREGEXTOOL", "PAPH", "PGITLABCI", "PCIRCLECI", "PHEROKU", "PREDISMANAGER", "PZEROCODE", "PSTORMSECTIONS",
            "PSENTRYINTEG", "PREDISTOOLS", "PFUZYFIPC", "PBITRISECI", "PQTSQSSEDITOR", "PAPPLETRUNNER", "PDATABASE",
            "PHPEAPLUGIN", "PLEP", "PHPBUILDER", "PMATERIALHC", "PCDMQTTCLIENT", "PISCRATCH", "PRSMGNL", "PCAPELASTIC",
            "PASTOCK", "PCAPREDIS", "PBEANCONVERTER", "PELSA", "PDJANGOTPLPEP", "PQUERYFLAG", "PNGINX", "PKSEXPLORER",
            "PZKA", "PCDAPIRUNNER", "PNEONPRO", "PMBCODEHELPPRO", "PCODEREFACTORAI", "PXSDVISUALIZER", "PSPRINGBOOTIDEA",
            "PEXCELEDITOR", "PGITLAB", "PYAPIQUICKTYPE", "PTERMINAL", "PWIREMOCHA", "PDYNAMODB", "PFASTSHELL", "PJSONNETEMLSUP",
            "PPHPHOUDINI", "POXYXSDJSONSCH", "PQUARKUSHELPER", "PWGCODECREATOR", "PCIINTG", "PDBDATABASETOOL", "PNGROK",
            "PKARATE", "PMATERIALEXTRAS", "PJSONTOANYLANGU", "PMATERIALCUSTOM", "PMATERIALLANG", "PMATERIALFRAME", "PRANCHER",
            "PREDISCLIHELPER", "PSCREENCODEPRO", "PCODEKITS", "PREDISS", "PAWSQLADVISOR", "PLATTEPRO", "PGERRYTHEMESPRO",
            "PUNIAPPSUPPORT", "POPENAPICRUDWIZ", "PGOPARSER", "PNEXTSKETCH", "PNETLIFY", "PGERRYCYBERPUNK", "PTLDRAI", "PBREWBUNDLE",
            "PGERRYSPACE", "PKAFKAIDE", "PGITHUBCI", "PGERRYNATURE", "PEXTENSION", "PSKOL", "PGERRYCHERRY", "PGERRYCOFFEE",
            "PCONNECTUI", "POXYJSONCONVERT", "PDOYTOWIN", "PGERRYAURORA", "PWXUFQYRHZCRSEO", "PWAUFKYVHQCRXEO", "PSQLFLUFFLINTER",
            "PMAGE", "PTAILWINDTOOLS", "PTRAVISCI", "PMONGOEXPERT", "PNEXTSKETCHTWO", "PWXUQQYVOXCRSEO", "PBUILDMON", "PJETCLIENT",
            "PAICODING", "PCAICOMMITAPP", "PCHATGPTCODING", "POLYBPMNGDNEXT", "PARMADILLO", "PVERILOGLANGUAG", "PNOSQLNAVMDB",
            "PCUEFY", "PCOMPOSEHAMMER", "PGPTASSISTANT", "PDTOBUDDY", "PNPMPACKAGEJSON", "PAZURECODING", "PGITLABCICD", "PSENTRY",
            "PKAFKA", "PSRCODEGEN", "PSOURCESYNCPRO", "PAZD", "PWXUQRYTOXCRSEO", "PPOLARISTOMCATS", "PMYBATISFIELDAD", "PIMAGETOVECTOR",
            "PDATAGRAPH", "POXYJSONSCHGEN", "PSPEECHTOTEXT", "PMYSQLPROXY", "PFASTREQUEST", "PMYBATISHELPER", "PREDIS",
            "RSC",   "RSF",  "RC",   "RS0", "RSV",  "RSU", "DP", "PDB",
            "PWS",  "PCWMP", "PPS", "PGO", "PPC", "PRB", "PSW", "RS"
        ,"AIP"
    };

    public static final String[] pycodes = {
            "DPN", "DB", "PS", "II", "RSC", "GO", "DM", "RSF", "PC", "RC", "CL", "WS", "RD", "RS0", "RM", "AC", "RSV", "DC", "RSU", "DP", "PDB",
            "PWS", "PSI", "PCWMP", "PPS", "PGO", "PPC", "PRB", "PSW", "RS"
    };

    /**
     *  如果不知道对应的ide的code是啥，可通过解析网络中的那些已经过期的激活码中的base64字符拿到
     *  比如pycodes就是这么拿到的，从generator 方法可激活码在以“-”为分割线的第2段
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        // IDEA code
        //generator("II", "PSI", "PCWMP");
        System.out.println("------------------------------------------------------------------");
        // pycodes 是网上随便找的一个验证码解析base64得到的 ：https://www.bilibili.com/read/cv34966236/?jump_opus=1
        // 我电脑的idea2023.3 和 PyCharm 2022.3 都可以激活
        generator(DEFAULT_CODES);
    }

    private static void generator(String... codes) throws Exception {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) certificateFactory.generateCertificate(Files.newInputStream(Paths.get("./ca.crt")));

        // 自己修改 license内容
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 激活码激活时限，100年
        calendar.add(Calendar.YEAR, 100);
        String date = DateUtil.formatDate(calendar.getTime());
        codes = codes == null ? DEFAULT_CODES : codes;
        // licenseId随便写
        String licenseId = "tiger";
        LicensePart licensePart = new LicensePart(licenseId, codes, date);
        byte[] licensePartBytes = licensePart.toString().getBytes(StandardCharsets.UTF_8);
        String licensePartBase64 = Base64.getEncoder().encodeToString(licensePartBytes);

        PrivateKey privateKey = getPrivateKey();


        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        signature.update(licensePartBytes);
        byte[] signatureBytes = signature.sign();

        String sigResultsBase64 = Base64.getEncoder().encodeToString(signatureBytes);
        // Combine results as needed
        String result = licenseId + "-" + licensePartBase64 + "-" + sigResultsBase64 + "-" + Base64.getEncoder().encodeToString(cert.getEncoded());
        System.out.println(result);
    }


    static PrivateKey getPrivateKey() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        PEMParser pemParser = new PEMParser(new FileReader(FileUtil.getAbsolutePath("./ca.key")));
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
        Object object = pemParser.readObject();
        KeyPair kp = converter.getKeyPair((PEMKeyPair) object);
        return kp.getPrivate();
    }

}
package club.gclmit.chaos.music.other;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/9 19:35
 * @version: V1.0
 */
public class XmlTest {

    public static void main(String[] args) throws DocumentException, FileNotFoundException {
        String xml = "<command-lable-xwl78-qq-music>\n" +
                "  <cmd value=\"1057\" verson=\"729\">\n" +
                "    <result retcode=\"0\">0</result>\n" +
                "    <reason/>\n" +
                "    <num>0</num>\n" +
                "    <url seq=\"0\" musicid=\"121577504\" songtype=\"0\" scode=\"0\" acode=\"0\">\n" +
                "      <singer> <![CDATA[]]> </singer>  \n" +
                "      <album> <![CDATA[]]> </album>  \n" +
                "      <s_l>http://y.gtimg.cn/music/photo_new/T001R150x150M0000020GatO16MYW5.jpg</s_l>  \n" +
                "      <s_m>http://y.gtimg.cn/music/photo_new/T001R90x90M0000020GatO16MYW5.jpg</s_m>  \n" +
                "      <s_s>http://y.gtimg.cn/music/photo_new/T001R68x68M0000020GatO16MYW5.jpg</s_s>  \n" +
                "      <s_sl>http://y.gtimg.cn/music/photo_new/T001R500x500M0000020GatO16MYW5.jpg</s_sl>  \n" +
                "      <a_l>http://y.gtimg.cn/music/photo_new/T002R150x150M000001ZaCQY2OxVMg.jpg</a_l>  \n" +
                "      <a_m>http://y.gtimg.cn/music/photo_new/T002R90x90M000001ZaCQY2OxVMg.jpg</a_m>  \n" +
                "      <a_s>http://y.gtimg.cn/music/photo_new/T002R68x68M000001ZaCQY2OxVMg.jpg</a_s>  \n" +
                "      <a_sl>http://y.gtimg.cn/music/photo_new/T002R500x500M000001ZaCQY2OxVMg.jpg</a_sl> \n" +
                "    </url> \n" +
                "  </cmd>\n" +
                "</command-lable-xwl78-qq-music>";

        System.out.println(xml);
//        xml = xml.substring(xml.indexOf("!")+3,xml.length()-4);
//        System.out.println(xml);

        SAXReader reader = new SAXReader();
        InputStream in = new FileInputStream(new File("src/test/rescoures/pic.xml"));


        Document doc = reader.read(in);

        Document document = DocumentHelper.parseText(xml);

        Node node = doc.selectSingleNode("//url/a_sl");
        Node node1 = document.selectSingleNode("//url/a_sl");
        System.out.println(node.getText());
        System.out.println(node1.getText());


//        Element e = (Element) ;
//        Object o = doc.selectObject("//url/@a_sl");
//        System.out.println(e.getText());
//        Element root = doc.getRootElement();
//
//
//        System.out.println(root.asXML());
    }
}

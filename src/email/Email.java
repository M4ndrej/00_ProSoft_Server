/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package email;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author Andrej
 */
public class Email {
    private Session novaSesija=null;
    private MimeMessage poruka=null;
    
    public static void autentifikacija(String primalac, String kod) throws MessagingException{
        Email por=new Email();
        por.postaviPropertie();
        por.draft(primalac, kod);
        por.posalji();
    }

    private Email() {
    }
    
    
    
    
    private void postaviPropertie(){
        Properties properties=System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        novaSesija=Session.getDefaultInstance(properties, null);
    }
    
    private MimeMessage draft(String email, String kod) throws AddressException, MessagingException{
        String primalac=email;
        
        String subject="Aktivacija naloga";
        
        String teloPoruke="Jednokratni kod za prijavljivanje na Vas nalog \n"
                + "u okviru sluzbe za upravljanje projektima je: "+kod+".\n"
                + "Molimo Vas, da prosledjeni kod ne delite, kao i da prilikom \n"
                + "prve prijave u aplikaciju konfigurisete novu lozinku, kao i \n"
                + "sve neophodne podatke Vaseg naloga.\n"
                + "Srdacno, \n"
                + "sluzba za upravljanje projektima.\n"
                + "Adresa: Novi Beograd, Ulica 27 \n"
                + "Kontakt: +381 11 123 123";
       
        poruka=new MimeMessage(novaSesija);
        
        poruka.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        
        poruka.setSubject(subject);
        
        MimeMultipart okvir=new MimeMultipart();
        
        MimeBodyPart telo=new MimeBodyPart();
        telo.setContent(teloPoruke, "text/plain");
        
        okvir.addBodyPart(telo);
        
        poruka.setContent(okvir);
        
        return poruka;
        
    }
    
    private void posalji() throws NoSuchProviderException, MessagingException{
        String from="sluzbaupravljanjeprojektima@gmail.com";
        String sifra="seru qtiw cgux ppch";
        String host="smtp.gmail.com";
        Transport transport=novaSesija.getTransport("smtp");
        transport.connect(host, from, sifra);
        transport.sendMessage(poruka, poruka.getAllRecipients());
        transport.close();
        System.out.println("Uspesno poslato!");
    }
    
}

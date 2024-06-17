package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class AppTest {

    private String userName = "seu-email";
    private String password = "sua senha de aplicativo gmail";

    @org.junit.Test
    public void testeEmail(){
        try{
            /*Olhe as configurações de smtp do seu email*/
            final Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");//autorização
            properties.put("mail.smtp.starttls.enable", "true");//segurança ou Autenticação
            properties.put("mail.smtp.host", "smtp.gmail.com");//servidor gmail Google
            properties.put("mail.smtp.port", "587");//porta do servidor
            properties.put("mail.smtp.socketFactory.port", "587");//Porta conectada pelo socket
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//Classe socket de conexão ao SMTP

            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            });

            Address[] toUser = InternetAddress.parse("email que ira enviar");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));//quem esta enviando
            message.setRecipients(Message.RecipientType.TO, toUser);//email de destino
            message.setSubject("Chegou um email enviado com java");// assunto do email
            message.setText("Ola meu chefe, enviamos a primeira mensagem em Java no email");

            Transport.send(message);

        }catch (Exception e){
            e.printStackTrace();
        }



    }

}

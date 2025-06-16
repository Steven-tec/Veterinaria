package com.steven.manejodesesiones.utils;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailSender {
    // Dirección de correo electrónico desde la cual se enviarán los emails
    private static final String EMAIL = "casbascangosteven@gmail.com";
    // Contraseña o clave de aplicación de Gmail para autenticación SMTP
    private static final String PASSWORD = "xpzx jzxs gcqc rnno"; // importante: clave de aplicación de Gmail

    // Método estático para enviar un correo electrónico
    // Parámetros: destinatario, asunto y contenido del mensaje
    public static void sendEmail(String to, String subject, String content) {
        // Configuración de propiedades para la sesión SMTP de Gmail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP de Gmail
        props.put("mail.smtp.port", "587"); // Puerto SMTP con STARTTLS
        props.put("mail.smtp.auth", "true"); // Requiere autenticación
        props.put("mail.smtp.starttls.enable", "true"); // Habilita STARTTLS para cifrado

        // Crea una sesión autenticada con las credenciales del email remitente
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // Retorna el usuario y contraseña para autenticación SMTP
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            // Crea un nuevo mensaje MIME
            Message message = new MimeMessage(session);
            // Establece el remitente del correo
            message.setFrom(new InternetAddress(EMAIL));
            // Establece el destinatario o destinatarios
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // Establece el asunto del mensaje
            message.setSubject(subject);
            // Establece el contenido de texto del mensaje
            message.setText(content);
            // Envía el mensaje mediante el transporte SMTP
            Transport.send(message);
        } catch (MessagingException e) {
            // Imprime la traza de error si ocurre una excepción al enviar el correo
            e.printStackTrace();
        }
    }
}



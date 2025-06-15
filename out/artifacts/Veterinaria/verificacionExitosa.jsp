<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cuenta verificada</title>
    <style>
        body {
            background: linear-gradient(135deg, #4CAF50 0%, #4CAF50 100%);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #333;
        }

        .verification-container {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 20px;
            padding: 40px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            backdrop-filter: blur(10px);
            text-align: center;
            max-width: 500px;
            width: 90%;
            animation: slideInUp 0.8s ease-out;
        }

        .success-icon {
            font-size: 4em;
            color: #4CAF50;
            margin-bottom: 20px;
            animation: bounceIn 1s ease-out 0.3s both;
        }

        h2 {
            color: #2E7D32;
            font-size: 2.2em;
            font-weight: 600;
            margin-bottom: 20px;
            text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            animation: fadeIn 1s ease-out 0.5s both;
        }

        .success-message {
            font-size: 1.2em;
            color: #555;
            margin-bottom: 30px;
            line-height: 1.6;
            animation: fadeIn 1s ease-out 0.7s both;
        }

        .login-button {
            display: inline-block;
            background: linear-gradient(135deg, #4CAF50, #66BB6A);
            color: white;
            text-decoration: none;
            padding: 15px 35px;
            border-radius: 50px;
            font-size: 1.1em;
            font-weight: 600;
            letter-spacing: 0.5px;
            text-transform: uppercase;
            transition: all 0.3s ease;
            box-shadow: 0 5px 15px rgba(76, 175, 80, 0.3);
            animation: fadeIn 1s ease-out 0.9s both;
        }

        .login-button:hover {
            transform: translateY(-3px);
            box-shadow: 0 8px 25px rgba(76, 175, 80, 0.4);
            background: linear-gradient(135deg, #66BB6A, #4CAF50);
        }

        .login-button:active {
            transform: translateY(-1px);
        }

        .decorative-element {
            position: absolute;
            width: 100px;
            height: 100px;
            border-radius: 50%;
            background: rgba(255, 255, 255, 0.1);
            animation: float 6s ease-in-out infinite;
        }

        .decorative-element:nth-child(1) {
            top: 10%;
            left: 10%;
            animation-delay: 0s;
        }

        .decorative-element:nth-child(2) {
            top: 20%;
            right: 15%;
            width: 150px;
            height: 150px;
            animation-delay: 2s;
        }

        .decorative-element:nth-child(3) {
            bottom: 15%;
            left: 20%;
            width: 80px;
            height: 80px;
            animation-delay: 4s;
        }

        .decorative-element:nth-child(4) {
            bottom: 25%;
            right: 10%;
            width: 120px;
            height: 120px;
            animation-delay: 1s;
        }

        /* Animaciones */
        @keyframes slideInUp {
            from {
                opacity: 0;
                transform: translateY(50px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes bounceIn {
            0% {
                opacity: 0;
                transform: scale(0) rotate(-180deg);
            }
            50% {
                opacity: 1;
                transform: scale(1.2) rotate(-90deg);
            }
            100% {
                opacity: 1;
                transform: scale(1) rotate(0deg);
            }
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes float {
            0%, 100% {
                transform: translateY(0px) rotate(0deg);
            }
            50% {
                transform: translateY(-20px) rotate(180deg);
            }
        }

        /* Responsive design */
        @media (max-width: 768px) {
            .verification-container {
                padding: 30px 20px;
                margin: 20px;
            }

            h2 {
                font-size: 1.8em;
            }

            .success-message {
                font-size: 1.1em;
            }

            .login-button {
                padding: 12px 25px;
                font-size: 1em;
            }

            .success-icon {
                font-size: 3em;
            }
        }
    </style>
</head>
<body>
<div class="decorative-element"></div>
<div class="decorative-element"></div>
<div class="decorative-element"></div>
<div class="decorative-element"></div>

<div class="verification-container">
    <div class="success-icon">✅</div>
    <h2>¡Tu cuenta ha sido verificada exitosamente!</h2>
    <p class="success-message">Ya puedes iniciar sesión en el sistema.</p>
    <a href="login.jsp" class="login-button">Ir al Login</a>
</div>
</body>
</html>

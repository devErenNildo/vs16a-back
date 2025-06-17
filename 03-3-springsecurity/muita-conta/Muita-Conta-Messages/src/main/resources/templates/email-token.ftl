<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Bem-vindo ao Fake-Bank</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            background-color: #ffffff;
            max-width: 600px;
            margin: 30px auto;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        .header {
            background-color: #4CAF50;
            color: white;
            padding: 20px;
            text-align: center;
        }
        .content {
            padding: 30px;
        }
        .content p {
            line-height: 1.5;
        }
        .footer {
            background-color: #f1f1f1;
            color: #777;
            text-align: center;
            font-size: 12px;
            padding: 15px;
        }
        .highlight {
            color: #4CAF50;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h2>${token}</h2>
    </div>
    <div class="content">
        <p>Olá <span class="highlight">${nome}</span>,</p>
        <p>Esse é o seu token de acesso para finalizar a criação da sua conta.</p>
        <p>Se tiver alguma dúvida ou precisar de ajuda, é só entrar em contato com o suporte através do e-mail <span class="highlight">${emailSuporte}</span>.</p>
        <p>Esperamos que você aproveite ao máximo o nosso sistema!</p>
    </div>
    <div class="footer">
        &copy; ${.now?string("yyyy")} Fake-Bank - Todos os direitos reservados.
    </div>
</div>
</body>
</html>

import csv
import webbrowser
from time import sleep
import pywhatkit as kit
import pyautogui

webbrowser.open('https://web.whatsapp.com/')
sleep(30)

csv_file_path = "/home/mathe/Desktop/Portfolio_MatheusdaCruz/PequenaAutomacaoWPP/teste.csv"

with open(csv_file_path, 'r', encoding='utf-8') as arquivo_csv:
    leitor_csv = csv.reader(arquivo_csv)
    for linha in leitor_csv:
        if len(linha) >= 2:
            nome = linha[0]
            numero = linha[1]
            mensagem = f'Olá {nome}, tudo bem? Esta é uma mensagem automática enviada pelo meu script Python!'
            try:
                kit.sendwhatmsg_instantly(f"+{numero}", mensagem, 15, True, 3)
                sleep(5)
                pyautogui.press('enter')  
                print(f"Mensagem enviada para {nome}")
            except Exception as e:
                print(f'Erro ao enviar mensagem para {nome}: {e}')
                with open('erros.csv', 'a', newline='', encoding='utf-8') as arquivo:
                    arquivo.write(f'{nome}, {numero}\n')
            sleep(10)
        else:
            print(f"Linha inválida ignorada: {linha}")
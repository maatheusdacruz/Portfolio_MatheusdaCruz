import openpyxl
from urllib.parse import quote
import webbrowser
from time import sleep
import pywhatkit as kit
# import pyautogui

webbrowser.open('https://web.whatsapp.com/')
sleep(30)

workbook = openpyxl.load_workbook("/home/mdcl/Desktop/python/botwhatsapp/teste2.xlsx")
pagina_clientes = workbook['Sheet1']

for linha in pagina_clientes.iter_rows(min_row=1):
    nome = linha[0].value
    numero = linha[1].value

    mensagem = f'Olá {nome}, como esta?'

    # try:
    #     link_mensagem_whatsapp = f'https://web.whatsapp.com/send?phone={numero}&text={quote(mensagem)}'
    #     webbrowser.open(link_mensagem_whatsapp)
    #     sleep(10)
    #     seta = pyautogui.locateCenterOnScreen('seta.png')
    #     sleep(10)
    #     pyautogui.click(seta[0], seta[1])
    #     sleep(10)
    #     pyautogui.hotkey('ctrl', 'w')
    #     sleep(10)
    # except: 
    #     print(f'Não foi possível enviar mensagem para para {nome}')
    #     with open('erros.cv', 'a', newline='', encoding='utf-8') as arquivo:
    #         arquivo.write(f'{nome}, {numero}')
    try:
        kit.sendwhatmsg_instantly(f"+{numero}", mensagem, 15, True, 3)
        print(f"Mensagem enviada para {nome}")
    except Exception as e:
        print(f'Erro ao enviar mensagem para {nome}: {e}')
        with open('erros.csv', 'a', newline='', encoding='utf-8') as arquivo:
            arquivo.write(f'{nome}, {numero}\n')

    sleep(10)  
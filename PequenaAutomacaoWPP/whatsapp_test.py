import requests
import openpyxl

TOKEN = "4290871"
# PHONE = "+553288611550"  
# MESSAGE = "Agora estou usando a api"


workbook = openpyxl.load_workbook("/home/mdcl/Desktop/python/botwhatsapp/teste2.xlsx")
pagina_clientes = workbook['Sheet1']

for linha in pagina_clientes.iter_rows(min_row=1):
    nome = linha[0].value
    numero = linha[1].value

    mensagem = f'Olá {nome}, como esta?'
    url = f"https://api.callmebot.com/whatsapp.php?phone={numero}&text={mensagem}&apikey={TOKEN}"


response = requests.get(url)
print(response.text)  

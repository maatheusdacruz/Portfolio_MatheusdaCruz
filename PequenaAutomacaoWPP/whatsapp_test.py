import requests
import csv
import time

TOKEN = "4290871"
# PHONE = "+553288611550"  
# MESSAGE = "Agora estou usando a api"

# Carregando o arquivo CSV corretamente
csv_file_path = "/home/mathe/Desktop/Portfolio_MatheusdaCruz/PequenaAutomacaoWPP/teste.csv"

with open(csv_file_path, 'r', encoding='utf-8') as arquivo_csv:
    leitor_csv = csv.reader(arquivo_csv)
    
    # Pular o cabeçalho se existir
    # next(leitor_csv, None)  # Descomente se o CSV tiver cabeçalho
    
    for linha in leitor_csv:
        if len(linha) >= 2:  # Verificar se a linha tem pelo menos 2 colunas
            nome = linha[0]
            numero = linha[1]
            
            # Loop para enviar 10 mensagens para cada contato
            for i in range(10):
                mensagem = f'Atende!!!'
                url = f"https://api.callmebot.com/whatsapp.php?phone={numero}&text={mensagem}&apikey={TOKEN}"
                
                try:
                    response = requests.get(url)
                    
                    # Verificar se houve erro na API
                    if "APIKey is invalid" in response.text:
                        print(f"❌ ERRO: API Key inválida! Mensagem {i+1} para {nome}")
                        break  # Para de tentar enviar para este contato
                    elif "Too many requests" in response.text:
                        print(f"⚠️  Rate limit atingido! Aguardando mais tempo... Mensagem {i+1} para {nome}")
                        time.sleep(10)  # Aguarda mais tempo quando há rate limit
                        continue  # Tenta novamente
                    else:
                        print(f"✅ Mensagem {i+1} enviada para {nome} ({numero}): {response.text}")
                    
                    # Aguardar mais tempo entre as mensagens para evitar rate limit
                    time.sleep(5)  # Aumentado para 5 segundos
                    
                except Exception as e:
                    print(f"Erro ao enviar mensagem {i+1} para {nome}: {e}")
        else:
            print(f"Linha inválida ignorada: {linha}")  

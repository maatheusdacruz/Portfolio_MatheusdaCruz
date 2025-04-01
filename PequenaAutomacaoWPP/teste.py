import sys
import psycopg2

try:
    conn = psycopg2.connect("dbname='teste2' user='postgres' host='localhost' port='5432' password='12345678'")
except psycopg2.DatabaseError:
    sys.exit('Failed to connect to database')

try:
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM cars ")
    dbRecord = cursor.fetchone()

    if dbRecord == None:
        print('ERROR: First record not found', file=sys.stderr)
    else:
        print('Loaded {}'.format(dbRecord))
    dbRecordId = dbRecord[1]

    conn.commit()
    cursor.close()
except (Exception, psycopg2.DatabaseError) as error:
    print(error)
finally:
    if conn is not None:
        conn.close()
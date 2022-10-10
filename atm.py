from datetime import datetime
import os

total_money = 1000
transaction_history = []

def display_menu():
    print("Hola Martin, Bienvenido\n\n")
    print(".......::: MENU :::.......")
    print("1. Consultar saldo")
    print("2. Retirar saldo")
    print("3. Histórico de movimientos")

def display_submenu():
    print("\n\n :: Submenu :: ")
    print("1. Regresar al menu principal")
    print("2. Salir")

    option = int(input("¿Que deseas hacer ahora? "))
    if option == 2:
        clean_console()
        print("Nos vemos Martin")
        exit()
    elif option != 1:
        print("Opción invalida, regresando al menu principal")
    else:
        clean_console()

def check_money():
    clean_console()
    print(f'Saldo actual ${total_money}')
    display_submenu()

def withdraw_cash():
    global total_money

    clean_console()
    if total_money == 0:
        print("No tienes fondos suficientes")
    else:
        while True:
            try:
                retiro = int(input("Cantidad a retirar: "))

                if retiro > total_money:
                    print("Saldo insuficiente")
                else:
                    total_money -= retiro
                    clean_console()
                    print("Retiro de saldo exitoso!")
                    print(f'Saldo disponible: {total_money}')
                    add_transaction_history(retiro)
                    break
            except ValueError:
                clean_console()
                print("Ingresa una cantidad")
    
    display_submenu()

def add_transaction_history(money_poped):
    global total_money, transaction_history
    today = datetime.now()

    element = {}
    element['fecha'] = f'{today.day}/{today.month}/{today.year}'
    element['tiempo'] = f'{today.hour}:{today.minute}:{today.second}'
    element['Cantidad retirada'] = money_poped
    element['Cantidad anterior'] = money_poped + total_money
    transaction_history.append(element)

def display_transaction_history():
    clean_console()

    if len(transaction_history) == 0:
        print("No tienes movimientos")
    else:
        print("Histórico de movimientos")

        for transaction in transaction_history:
            print('\n\n------ Movimiento -----')
            for prop in transaction:
                print(f'{prop}: {transaction[prop]}')
            
    display_submenu()

def clean_console():
    os.system('cls')
    

print("Cajero Automativo")
pin = input("Ingresa tu PIN: ")
counter = 0

while pin != "1235":
    print("PIN incorrecto")
    counter += 1
    if counter == 3:
        print("Demasiados intentos por hoy, nos vemos!!!")
        exit()
    else:
        clean_console()
        pin = input("Ingresa nuevamente tu PIN: ")

while True:
    display_menu()
    try:
        option = int(input("¿Que operación deseas realizar? "))
        if option == 1:
            check_money()
        elif option == 2:
            withdraw_cash()
        elif option == 3:
            display_transaction_history()
        else:
            clean_console()
            print("Opción no valida")
    except ValueError:
        clean_console()
        print("Ingresa datos númericos unicamente")

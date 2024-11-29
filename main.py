import tkinter as tk
# Класс TicTacToe реализует интерфейс игры "Крестики-нолики"
class TicTacToe:
    def __init__(self):
        self.window = tk.Tk() # Инициализация окна приложения
        self.window.title("Крестики-нолики") # Установлен заголовок окна
        self.buttons = [] # Создан список для хранения кнопок игрового поля

        # Создание игрового поля 3x3
        for i in range(3): # Создание игрового поля 3x3
            for j in range(3): # Создание игрового поля 3x3 
                button = tk.Button( # Создание кнопки для игрового поля
                    self.window, # Указан родительский элемент - главное окно
                    text='',  # Установлен пустой текст на кнопке
                    font=('Times New Roman', 25), # Указан шрифт текста
                    width=10, # Установлена ширина кнопки
                    height=5  # Установлена высота кнопки 
                )
                button.grid(row=i, column=j) # Размещение кнопки в сетке 
                self.buttons.append(button) # Добавление кнопки в список кнопок
                

    def run(self):
        self.window.mainloop() # Запуск обработки событий приложения 

if __name__ == "__main__":
    game = TicTacToe() # Создание экземпляра игры TicTacToe
    game.run()  # Запуск основного цикла приложения

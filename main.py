import tkinter as tk
# Класс TicTacToe реализует интерфейс игры "Крестики-нолики"
class TicTacToe:
    def __init__(self):
        self.window = tk.Tk() # Инициализация окна приложения
        self.window.title("Крестики-нолики") # Установлен заголовок окна
        self.current_player = 'X' # Начинаем с игрока X
        self.board = ['' for _ in range(9)] # Хранит состояние игры
        self.buttons = [] # Создан список для хранения кнопок игрового поля

        # Создание сетки игрового поля 3x3
        for i in range(3): # Создание сетки игрового поля 3x3
            for j in range(3): # Создание игрового поля 3x3 
                button = tk.Button( # Создание кнопки для игрового поля
                    self.window, # Указан родительский элемент главное окно
                    text='',  # Установлен пустой текст на кнопке
                    font=('Times New Roman', 25), # Указан шрифт текста
                    width=10, # Установлена ширина кнопки
                    height=5  # Установлена высота кнопки 
                )
                button.grid(row=i, column=j) # Размещение кнопки в сетке 
                self.buttons.append(button) # Добавление кнопки в список кнопок
                
        reset_button = tk.Button(
            self.window,
            text="Новая игра",
            command=self.reset_game
        )
        reset_button.grid(row=3, column=1)
                
    def button_click(self, row, col): # Обработка клика по кнопке
        index = 3 * row + col # Вычисляет индекс в списке board по строке и столбцу
        if self.board[index] == '': # Если ячейка пустая
            self.board[index] = self.current_player # Заполняем ячейку текущим игроком
            self.buttons[index].config(text=self.current_player, fg="navy") # Обновляем текст на кнопке и добавляем цвет темно синий

            if self.check_winner(): # Проверяем, есть ли победитель
                messagebox.showinfo("Победа!", f"Игрок {self.current_player} победил!") 
                self.reset_game() # Сброс игры
            elif '' not in self.board: # Если все ячейки заполнены и победителя нет
                messagebox.showinfo("Ничья!", "Игра окончена вничью!")
                self.reset_game() # Сброс игры
            else:
                self.current_player = "O"
                sekf.ai_move()
                
     def button_click(self, row, col):
        index = 3 * row + col
        if self.board[index] == '':
            self.board[index] = self.current_player
            self.buttons[index].config(text=self.current_player, fg="navy")

            if self.check_winner():
                messagebox.showinfo("Победа!", f"Игрок {self.current_player} победил!")
                self.reset_game()
            elif '' not in self.board:
                messagebox.showinfo("Ничья!", "Игра окончена вничью!")
                self.reset_game()
            else:
                self.current_player = 'O'
                self.ai_move()

    def ai_move(self):
        best_score = float('-inf')
        best_move = None

        for i in range(len(self.board)):
            if self.board[i] == '':
                self.board[i] = 'O'
                score = self.minimax(self.board, 0, False)
                self.board[i] = ''
                if score > best_score:
                    best_score = score
                    best_move = i

        if best_move is not None:
            self.buttons[best_move].config(text='O', fg='red')
            self.board[best_move] = 'O'

            if self.check_winner():
                messagebox.showinfo("Победа!", "Компьютер победил!")
                self.reset_game()
            elif '' not in self.board:
                messagebox.showinfo("Ничья!", "Игра окончена вничью!")
                self.reset_game()
            else:
                self.current_player = 'X'
                
    def minimax(self, board, depth, is_maximizing):
        if self.check_winner_board(board, 'O'):
            return 1
        if self.check_winner_board(board, 'X'):
            return -1
        if '' not in board:
            return 0

        if is_maximizing:
            best_score = float('-inf')
            for i in range(len(board)):
                if board[i] == '':
                    board[i] = 'O'
                    score = self.minimax(board, depth + 1, False)
                    board[i] = ''
                    best_score = max(score, best_score)
            return best_score
        else:
            best_score = float('inf')
            for i in range(len(board)):
                if board[i] == '':
                    board[i] = 'X'
                    score = self.minimax(board, depth + 1, True)
                    board[i] = ''
                    best_score = min(score, best_score)
            return best_score
            
     def check_winner(self):
        return self.check_winner_board(self.board, self.current_player)
        
    def check_winner(self): # Метод проверки условий победы 
        for i in range(0, 9, 3):   # Проверка строк, проверяем каждые 3 подряд идущие ячейки 
            if self.board[i] == self.board[i + 1] == self.board[i + 2] != '': 
                return True # Если все три одинаковы и не пустые, победа
        for i in range(3):
            if self.board[i] == self.board[i + 3] == self.board[i + 6] != '': # Проверка столбцов, проверяем ячейки одного столбца
                return True
        if self.board[0] == self.board[4] == self.board[8] != '': # Проверка диагоналей, левая диагональ
            return True
        if self.board[2] == self.board[4] == self.board[6] != '': # Правая диагональ
            return True
            
        return False # Если ни одно условие не выполнено, победителя нет

    def reset_game(self): # Метод для сброса игры
        self.board = ['' for _ in range(9)] # Очищаем игровое поле
        self.current_player = 'X' # Возвращаем ход первому игроку
        for button in self.buttons: # Проходим по всем кнопкам
            button.config(text='') # Очищаем текст на кнопках

    def run(self):
    self.window.mainloop() # Запуск обработки событий приложения 

if __name__ == "__main__":
    game = TicTacToe() # Создание экземпляра игры TicTacToe
    game.run()  # Запуск основного цикла приложения

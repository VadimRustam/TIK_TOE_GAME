import tkinter as tk

class TicTacToe:
    def __init__(self):
        self.window = tk.Tk()
        self.window.title("Крестики-нолики")
        self.buttons = []

        for i in range(3):
            for j in range(3):
                button = tk.Button(
                    self.window,
                    text='',
                    font=('Times New Roman', 25),
                    width=10,
                    height=5
                )
                button.grid(row=i, column=j)
                self.buttons.append(button)

    def run(self):
        self.window.mainloop()

if __name__ == "__main__":
    game = TicTacToe()
    game.run()

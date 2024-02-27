#Murat Güven Kaya
#I made this project for Akbank Python Bootcamp
#This project was given as homework by Global Aİ HUB
print("-----Kütüphaneye Hoşgeldiniz-----")

class Library:
    def __init__(self):
        self.books = {}

    def add_book(self):
        book_id = input("kitap ID : ")
        title = input("Kitap Başlığı : ")
        author = input("Yazar : ")
        self.books[book_id] = {"title": title, "author": author}

    def remove_book(self, book_id):
        if book_id in self.books:
            del self.books[book_id]
            print("Book removed successfully!")
        else:
            print("Book not found")

    def display_books(self):
        print("Books available:")
        for book_id, book_info in self.books.items():
            print(f"{book_id}: {book_info['title']} by {book_info['author']}")

library = Library()

while True:
    print("1-) List Books\n2-) Add Books\n3-) Remove Books\n4-) Quit(Q)")
    islem = input("Lütfen Yapmak İstediğiniz İşlemi Seçin: ")
    if islem == '1':
        library.display_books()
    elif islem == '2':
        library.add_book()
    elif islem == '3':
        book_id = input("Kitap ID'si: ")
        library.remove_book(book_id)
    elif islem.upper() == 'Q':
        a = input("Tekrardan bakmak İstiyormusunuz(E/H): ")
        if a.upper() == 'H':
            break


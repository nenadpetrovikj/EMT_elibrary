import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Books from "../Books/BookList/books";
import ELibraryService from "../../repository/elibraryRepository";
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";
import Categories from "../Categories/categories";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      categoryTypes: [],
      authors: [],
      books: [],
      selectedBook: {},
    }
  }

  render() {
    return (
        <Router>
          <Header/>
          <main>
            <div className="container">
              <Route path={"/categories"} exact render={() =>
                  <Categories categoryTypes={this.state.categoryTypes} />} />
              <Route path={"/books/add"} exact render={() =>
                  <BookAdd categoryTypes={this.state.categoryTypes}
                           authors={this.state.authors}
                           onAddBook={this.addBook} />}/>
              <Route path={"/books/edit/:id"} exact render={() =>
                  <BookEdit categoryTypes={this.state.categoryTypes}
                            authors={this.state.authors}
                            onEditBook={this.editBook}
                            book={this.state.selectedBook} />}/>
              <Route path={"/books"} exact render={() =>
                  <Books books={this.state.books}
                         onMark={this.markBook}
                         onDelete={this.deleteBook}
                         onEdit={this.getBook} />}/>
              <Redirect to={"/books"}/>
            </div>
          </main>
        </Router>
    );
  }

  componentDidMount() {
    this.loadCategories();
    this.loadAuthors();
    this.loadBooks();
  }

  loadCategories = () => {
    ELibraryService.fetchCategories()
        .then((data) => {
          this.setState({
            categoryTypes: data.data
          })
        });
  }

  loadAuthors = () => {
    ELibraryService.fetchAuthors()
        .then((data) => {
          this.setState({
            authors: data.data
          })
        });
  }
  loadBooks = () => {
    ELibraryService.fetchBooks()
        .then((data) => {
          this.setState({
            books: data.data
          })
        });
  }

  deleteBook = (id) => {
    ELibraryService.deleteBook(id)
        .then(() => {
          this.loadBooks();
        });
  }

  addBook = (name, categoryType, author, availableCopies) => {
    ELibraryService.addBook(name, categoryType, author, availableCopies)
        .then(() => {
          this.loadBooks();
        });
  }

  editBook = (id, name, categoryType, author, availableCopies) => {
    ELibraryService.editBook(id, name, categoryType, author, availableCopies)
        .then(() => {
          this.loadBooks();
        });
  }

  getBook = (id) => {
    ELibraryService.getBook(id)
        .then((data) => {
          this.setState({
            selectedBook: data.data
          })
        });
  }

  markBook = (id) => {
    ELibraryService.markBook(id)
        .then(() => {
          this.loadBooks();
        });
  }
}

export default App;

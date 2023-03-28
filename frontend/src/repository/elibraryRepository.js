import axios from "../custom-axios/axios";

const ELibraryService = {
    fetchBooks: () => {
        return axios.get("/books");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name, categoryType, author, availableCopies) => {
        return axios.post("/books/add/", {
            "name" : name,
            "categoryType" : categoryType,
            "authorId" : author,
            "availableCopies" : availableCopies,
        });
    },
    editBook: (id, name, categoryType, author, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name" : name,
            "categoryType" : categoryType,
            "authorId" : author,
            "availableCopies" : availableCopies,
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    markBook: (id) => {
        return axios.get(`/books/mark/${id}`);
    },
    fetchCategories: () => {
        return axios.get("/books/categories");
    },
    fetchAuthors: () => {
        return axios.get("/authors");
    }
}

export default ELibraryService;
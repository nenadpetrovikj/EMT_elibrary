import React from "react";
import {useHistory} from "react-router-dom";

const BookAdd = (props) => {

    const history = useHistory();

    const [formData, updateFormData] = React.useState({
        name: "",
        categoryType: "NOVEL",
        author: 1,
        availableCopies: 0,
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const categoryType = formData.categoryType;
        const author = formData.author;
        const availableCopies = formData.availableCopies;

        props.onAddProduct(name, categoryType, author, availableCopies);
        history.push("/books");
    }


    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter book name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder="1"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="categoryType" className="form-control" onChange={handleChange}>
                            {props.categoryTypes.map((element) =>
                                <option value={element}>{element}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((element) =>
                                <option value={element.id}>{element.name}</option>
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary mt-2">Submit</button>
                </form>
            </div>
        </div>
    );
}

export default BookAdd;
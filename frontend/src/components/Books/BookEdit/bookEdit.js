import React from "react";
import {useHistory} from "react-router-dom";

const BookEdit = (props) => {

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
        const name = formData.name !== "" ? formData.name : props.book.name;
        const categoryType = formData.categoryType !== "NOVEL" ? formData.categoryType : props.book.categoryType;
        const author = formData.author !== 1 ? formData.author : props.book.author.id;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.categoryType;

        props.onEditProduct(props.book.id, name, categoryType, author, availableCopies);
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
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder={props.book.availableCopies}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="categoryType" className="form-control" onChange={handleChange}>
                            {props.categoryTypes.map((element) => {
                                if (props.book.categoryType === element)
                                    return <option selected={props.book.categoryType} value={element}>{element}</option>
                                else
                                    return <option value={element}>{element}</option>
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((element) => {
                                if (props.book.author !== undefined && props.book.author.id === element.id)
                                    return <option selected={props.book.author.id} value={element.id}>{element.name}</option>
                                else
                                    return <option value={element.id}>{element.name}</option>
                            })}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary mt-2">Submit</button>
                </form>
            </div>
        </div>
    );
}

export default BookEdit;
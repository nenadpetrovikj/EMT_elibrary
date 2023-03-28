import React from "react";
import {Link} from "react-router-dom";

const bookElement = (props) => {
    return (
        <tr>
            <td>{props.element.name}</td>
            <td>{props.element.categoryType}</td>
            <td>{props.element.author.name}</td>
            <td>{props.element.availableCopies}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger mx-1"}
                   onClick={() => props.onDelete(props.element.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info mx-1"} onClick={() => props.onEdit(props.element.id)} to={`/books/edit/${props.element.id}`}>Edit</Link>
                <a title={"Mark"} className={"btn btn-success mx-1"}
                   onClick={() => props.onMark(props.element.id)}>
                    Mark as Taken
                </a>
            </td>
        </tr>
    );
}

export default bookElement;
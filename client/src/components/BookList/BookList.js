import React from 'react';

import axios from 'axios';

export default class BookList extends React.Component {
    state = {
        books: []
    }

    componentDidMount() {
        axios.get(`/api/books`)
            .then(res => {
                const books = res.data;
                this.setState({books});
            })
    }

    render() {
        return (
            <ul>
                {this.state.books.map(person => <li>{person.title}</li>)}
            </ul>
        )
    }
}

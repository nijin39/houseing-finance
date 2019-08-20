import React, { Component } from 'react';
import axios from 'axios';
import SignUp from '../SingUp/SingUp';
import RecipeReviewCard from "../RecipeReviewCard/RecipeReviewCard";
import ImgMediaCard from "../ImgMediaCard/ImgMediaCard";
import BookList from "../BookList/BookList";

class App extends Component {
  state = {
    data: null
  };
  getPost = async () => {
    try {
      const response = await axios.get('/api/hello');
      console.log(response)
      this.setState({
        data: response.data
      });
    } catch (e) {
      console.log(e);
    }
  };
  componentDidMount() {
    this.getPost();
  }
  render() {
    if (!this.state.data) {
      return <div>로딩중...</div>;
    }
    const { title, body } = this.state.data;

    return (
      <div>
        <h1>{title}</h1>
        <p>{body}</p>
        <SignUp/>
        <RecipeReviewCard/>
        <ImgMediaCard/>
        <BookList/>
      </div>
    );
  }
}

export default App;
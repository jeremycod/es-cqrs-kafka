import React, {Component} from 'react';

import './App.css';
import Layout from "./components/Layout";
import Dashboard from "./components/Dashboard";


class App extends Component {
  constructor(props) {
    super(props);
    this.state = {title: ''};
  }

  async componentDidMount() {

  }

  render() {
    return (
    <Layout>
      <Dashboard></Dashboard>
    </Layout>
    );
  }
}

export default App;

/*
 * Copyright (C) 2021 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

import React, {Component} from 'react';

// Allows us to write CSS styles inside App.css, any any styles will apply to all components inside <App />
import "./App.css";

interface AppState {
    inputText: any
    enteredName: any
}

class App extends Component<{}, AppState> { // <- {} means no props.

    constructor(props: any) {
        super(props);
        this.state = {
            inputText: "",
            enteredName: null,
        };
    }

    handleChange = (event: React.ChangeEvent<HTMLInputElement>): void => {
        this.setState({
            inputText: event.currentTarget.value,
        })
    }

    handleSubmit = (event: any): void => {
        // TODO: implement this function that takes an "event" when the name
        // is submitted, changes the "entered name" to what the user entered,
        // and resets the text field by changing the "inputText" variable to an empty string.
    }

    render() {
        let greeting = "";
        if (this.state.enteredName) {
           greeting = "Hello, " + this.state.enteredName;
        }
        return (
            <div>
                <label>
                Name:
                <input value={this.state.inputText} onChange={this.handleChange}/>
                </label>
                <button /*onClick={ TODO: uncomment and fill inside the braces }*/>Submit</button>
                <p>
                    { greeting }
                </p>
            </div>
        );
    }

}

export default App;

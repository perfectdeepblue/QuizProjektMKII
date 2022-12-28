const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {questions: []};
    }
 
    componentDidMount() {
        client({method: 'GET', path: 'api/questions'}).done(respone => {this.setState({quetions: response.entity._embedded.questions});
        });
    }

    render() {
        return (
            <QuestionsList questions={this.state.questions}/>
        )
    }
}
class QuestionsList extends React.Component{
    render() {
        const questions = this.props.questions.map(question => 
            <Question key={question._links.self.href} question={question}/>
            );
            return (
                    <table>
                            <tbody>
                                <tr>
                                    <th>Pig Fucker</th>
                                    <th>Alternatives</th>
                                </tr>
                                {questions}
                            </tbody>
                    </table>
            )
    }
}
class Question extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.question.difficulty}</td>
                <td>{this.props.question.correct_answer}</td>
                <td>{this.props.question.wrong_answers}</td>
                <td>{this.props.question.category}</td>
                <td>{this.props.question.type}</td>
            </tr>
        )
    }
}
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
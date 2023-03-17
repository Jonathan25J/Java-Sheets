package global;


public class Card {
    private String question;
    private String answer;
    private String link;

    public Card(String question, String answer, String link) {
        this.question = question;
        this.answer = answer;
        this.link = link;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return question + "<>" + answer + "<>" + link;
    }
}

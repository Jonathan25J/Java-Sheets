package global;


import java.io.Serializable;
import java.util.Objects;

public class Card implements Serializable {
    private String question;
    private String answer;
    private String qlink;
    private String alink;

    public Card(String question, String answer, String qlink, String alink) {
        this.question = question;
        this.answer = answer;
        this.qlink = qlink;
        this.alink = alink;
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
    public String getQlink() {
        return qlink;
    }

    public void setQlink(String qlink) {
        this.qlink = qlink;
    }

    public String getAlink() {
        return alink;
    }

    public void setAlink(String alink) {
        this.alink = alink;
    }

    @Override
    public String toString() {
        return "Card: " +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", qlink='" + qlink + '\'' +
                ", alink='" + alink ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return question.equals(card.question) && answer.equals(card.answer) && qlink.equals(card.qlink) && alink.equals(card.alink);
    }
}

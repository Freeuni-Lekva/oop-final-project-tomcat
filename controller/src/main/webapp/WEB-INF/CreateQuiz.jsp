<%--
  Created by IntelliJ IDEA.
  User: muradi
  Date: 8/22/2023
  Time: 1:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style><%@include file="css/styles.css"%></style>
    <meta charset="UTF-8">
    <title>Create Quiz</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: indigo;
        }
        h4 {
            font-size: 12px;
            margin-bottom: 3px;
            text-align: center;
            color: #6B6B6B;
            font-style: italic;
        }
        label, select, input, textarea {
            font-size: 16px;
            margin-bottom: 10px;
            padding: 5px;
        }
        label {
            font-weight: bold;
            color: indigo;
        }
        button {
            font-size: 18px;
            padding: 10px 20px;
            background-color: indigo;
            color: white;
            border: none;
            cursor: pointer;
        }
        #questionsContainer {
        }
        .questionDiv {
            border: 1px solid indigo;
            padding: 15px;
            margin-bottom: 20px;
            background-color: lavenderblush;
        }
        #addQuestionBtn {
            margin-bottom: 15px;
            border-radius: 5px;
        }
        #addQuestionBtn:hover {
            background-color: #23527c;
            transform: scale(1.02);
        }
    </style>
</head>
<body>
<jsp:include page="UserFooter.jsp" />
<jsp:include page="SearchUser.jsp" />
<div class="create-quiz">
<h1>Create Quiz</h1>
<form action="CreateQuiz" method="post" style="overflow-y: scroll;overflow-x: hidden;">
    <input type="text" id="quizTitle" name="quizTitle" style="width: 97.3%;" placeholder="Title" required>

    <textarea id="quizDescription" name="quizDescription" rows="4" cols="50" style="width: 97.9%;border-radius: 5px;font-family: Arial, sans-serif;" placeholder="Description" required></textarea>
    <input type="hidden" id="numberOfQuestions" name="numberOfQuestions" value="1">

    <label><input type="checkbox" name="randomizeQuestions"> Randomize Questions</label><br>
    <label><input type="checkbox" name="onePageView"> One Page View</label><br>
    <label><input type="checkbox" name="immediateCorrection"> Immediate Correction</label><br>
    <label><input type="checkbox" name="practiceMode"> Practice Mode</label><br>

    <div id="questionsContainer">
    <br>
    </div>
    <button type="button" id="addQuestionBtn">Add Question</button>

    <button type="submit">Submit</button>
</form>
</div>
<script>
    const addQuestionBtn = document.getElementById("addQuestionBtn");
    const questionsContainer = document.getElementById("questionsContainer");
    const numberOfQuestionsInput = document.getElementById("numberOfQuestions");
    let questionCount = 0;

    addQuestionBtn.addEventListener("click", () => {
        const questionDiv = document.createElement("div");
        questionDiv.dataset.questionNumber = questionCount;
        const questionNum = questionDiv.dataset.questionNumber;
        questionDiv.className = "questionDiv";
        questionDiv.innerHTML = `
                                    <h3 style="color: indigo;">Question ${questionCount + 1}</h3>
                                    <label for="questionTypeEnum${questionNum}" style="color: indigo;font-weight: bold;">Question Type:</label>
                                    <select id="questionTypeEnum${questionNum}" name="questionTypeEnum${questionNum}" style="font-size: 16px;">
                                        <option value="" selected disabled>Select a question type</option>
                                        <option value="1">Question-Response</option>
                                        <option value="2">Fill in the Blank</option>
                                        <option value="3">Multiple Choice</option>
                                        <option value="4">Picture-Response</option>
                                    </select>
                                    <div id="questionTemplate${questionNum}">
                                    </div>
                                `;

        const questionTypeSelect = questionDiv.querySelector(`#questionTypeEnum${questionNum}`);
        const questionTemplateDiv = questionDiv.querySelector(`#questionTemplate${questionNum}`);

        questionTypeSelect.addEventListener("change", () => {
            questionTemplateDiv.innerHTML = getQuestionTemplate(questionTypeSelect.value, questionNum);

            if (questionTypeSelect.value === "3") {
                const optionsContainer = questionTemplateDiv.querySelector(`#optionsContainer${questionNum}`);
                const optionFieldsContainer = questionTemplateDiv.querySelector(`#optionFields${questionNum}`);
                const correctOptionSelect = optionsContainer.querySelector(`#correctOption${questionNum}`);
                const numOfOptionsInput = questionTemplateDiv.querySelector(`#numOfOptions${questionNum}`);

                correctOptionSelect.innerHTML = generateCorrectOptions(numOfOptionsInput.value);
                optionFieldsContainer.innerHTML = generateOptionFields(numOfOptionsInput.value, questionNum);

                numOfOptionsInput.addEventListener("input", () => {
                    correctOptionSelect.innerHTML = generateCorrectOptions(numOfOptionsInput.value);
                    optionFieldsContainer.innerHTML = generateOptionFields(numOfOptionsInput.value, questionNum);
                });
            }
        });

        questionsContainer.appendChild(questionDiv);
        questionCount++;
        numberOfQuestionsInput.value = questionCount;
    });
    function getQuestionTemplate(questionType, questionNumber) {
        switch (parseInt(questionType)) {
            case 1:
                return `
                    <input type="text" id="questionText${questionNumber}" name="questionText${questionNumber}" required placeholder="Question Text" style="width: 98%; padding: 8px; font-size: 25px;">
                    <br>
                    <h4>*separate all possible variations of the correct answer by |</h4>
                    <input type="text" id="answerText${questionNumber}" name="answerText${questionNumber}" required placeholder="Answer" style="width: 98%; padding: 8px; font-size: 25px;">
                    <br>
                    <input type="number" id="points${questionNumber}" name="points${questionNumber}" required placeholder="Points" style="width: 98%; padding: 8px; font-size: 25px;">
                `;
            case 2:
                return `
                    <input type="text" id="beforeGap${questionNumber}" name="beforeGap${questionNumber}" required placeholder="Before Gap" style="width: 98%; padding: 8px; font-size: 25px;">
                    <br>
                    <h4>*separate all possible variations of the correct answer by |</h4>
                    <input type="text" id="answer${questionNumber}" name="answer${questionNumber}" required placeholder="Answer" style="width: 98%; padding: 8px; font-size: 25px;">
                    <br>
                    <input type="text" id="afterGap${questionNumber}" name="afterGap${questionNumber}" required placeholder="After Gap" style="width: 98%; padding: 8px; font-size: 25px;">
                    <br>
                    <input type="number" id="points${questionNumber}" name="points${questionNumber}" required placeholder="Points" style="width: 98%; padding: 8px; font-size: 25px;">
                `;
            case 3:
                return `
                    <input type="text" id="questionText${questionNumber}" name="questionText${questionNumber}" required placeholder="Question Text" style="width: 98%; padding: 8px; font-size: 25px;">
                    <br>
                    <input type="number" id="numOfOptions${questionNumber}" name="numOfOptions${questionNumber}" required placeholder="Number of Options" style="width: 98%; padding: 8px; font-size: 25px;">
                    <div id="optionsContainer${questionNumber}">
                        <label for="options${questionNumber}" style="color: indigo;">Options:</label>
                        <br>
                        <!-- Input fields for options -->
                        <div id="optionFields${questionNumber}">
                        </div>
                        <br>
                        <label for="correctOption${questionNumber}" style="color: indigo;">Correct Option:</label>
                        <select id="correctOption${questionNumber}" name="correctOption${questionNumber}" required style="font-size: 16px;">
                            <!-- Options for correct answer will be added here dynamically -->
                        </select>
                    </div>
                    <br>
                    <input type="number" id="points${questionNumber}" name="points${questionNumber}" required placeholder="Points" style="width: 98%; padding: 8px; font-size: 25px;">
                `;
            case 4:
                return `
                    <input type="text" id="imageUrl${questionNumber}" name="imageUrl${questionNumber}" required placeholder="Image URL" style="width: 98%; padding: 8px; font-size: 25px;">
                    <br>
                    <input type="text" id="questionText${questionNumber}" name="questionText${questionNumber}" required placeholder="Question Text" style="width: 98%; padding: 8px; font-size: 25px;">
                    <br>
                    <h4>*separate all possible variations of the correct answer by |</h4>
                    <input type="text" id="answerText${questionNumber}" name="answerText${questionNumber}" required placeholder="Answer" style="width: 98%; padding: 8px; font-size: 25px;">
                    <br>
                    <input type="number" id="points${questionNumber}" name="points${questionNumber}" required placeholder="Points" style="width: 98%; padding: 8px; font-size: 25px;">
                `;
            default:
                return "";
        }
    }
    function generateOptionFields(numOfOptions, questionNumber) {
        let optionsHtml = '';

        for (let i = 1; i <= numOfOptions; i++) {
            optionsHtml += `
                <input type="text" id="option${questionNumber}_text${i}" name="option${questionNumber}_text${i}" placeholder="Option ${i}" style="width: 98%; padding: 8px; font-size: 16px;">
                <br>
            `;
        }

        return optionsHtml;
    }
    function generateCorrectOptions(numOfOptions) {
        let optionsHtml = '';

        for (let i = 1; i <= numOfOptions; i++) {
            optionsHtml += `<option value="${i}">Option ${i}</option>`;
        }

        return optionsHtml;
    }
    function validateForm() {
        const questionTypeSelects = document.querySelectorAll("[id^='questionTypeEnum']");

        for (const select of questionTypeSelects) {
            if (select.value === "") {
                alert("Please select a question type for all questions.");
                return false;
            }
        }
        return true;
    }

</script>


</body>
</html>



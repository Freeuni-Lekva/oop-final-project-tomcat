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
        label, select, input, textarea {
            font-size: 16px;
            margin-bottom: 10px;
            padding: 5px;
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
            margin-top: 20px;
        }
        .questionDiv {
            border: 1px solid indigo;
            padding: 15px;
            margin-bottom: 20px;
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
<h1>Create Quiz</h1>
<form action="CreateQuiz" method="post" onsubmit="return validateForm();">
<form action="CreateQuiz" method="post">
    <label for="quizTitle">Quiz Title:</label>
    <input type="text" id="quizTitle" name="quizTitle" required><br>

    <label for="quizDescription">Quiz Description:</label>
    <textarea id="quizDescription" name="quizDescription" rows="4" cols="50" required></textarea><br>
    <input type="hidden" id="numberOfQuestions" name="numberOfQuestions" value="0">

    <label><input type="checkbox" name="randomizeQuestions"> Randomize Questions</label><br>
    <label><input type="checkbox" name="onePageView"> One Page View</label><br>
    <label><input type="checkbox" name="immediateCorrection"> Immediate Correction</label><br>
    <label><input type="checkbox" name="practiceMode"> Practice Mode</label><br>

    <button type="button" id="addQuestionBtn">Add Question</button>

    <div id="questionsContainer">
    </div>

    <button type="submit">Submit</button>
</form>
</form>

<script>
    const addQuestionBtn = document.getElementById("addQuestionBtn");
    const questionsContainer = document.getElementById("questionsContainer");
    const numberOfQuestionsInput = document.getElementById("numberOfQuestions");
    let questionCount = 0;

    addQuestionBtn.addEventListener("click", () => {
        const questionDiv = document.createElement("div");
        questionDiv.className = "questionDiv";
        questionDiv.innerHTML = `
    <h3 style="color: indigo;">Question ${questionCount + 1}</h3>
    <label for="questionTypeEnum${questionCount}" style="color: indigo;">Question Type:</label>
    <select id="questionTypeEnum${questionCount}" name="questionTypeEnum${questionCount}" style="font-size: 16px;">
        <option value="" selected disabled>Select a question type</option>
        <option value="1">Question-Response</option>
        <option value="2">Fill in the Blank</option>
        <option value="3">Multiple Choice</option>
        <option value="4">Picture-Response</option>
    </select>
    <div id="questionTemplate${questionCount}">
    </div>
`;

        const questionTypeSelect = questionDiv.querySelector(`#questionTypeEnum${questionCount}`);
        const questionTemplateDiv = questionDiv.querySelector(`#questionTemplate${questionCount}`);

        questionTypeSelect.addEventListener("change", () => {
            questionTemplateDiv.innerHTML = getQuestionTemplate(questionTypeSelect.value, questionCount);

            if (questionTypeSelect.value === "3") {
                const optionsContainer = questionTemplateDiv.querySelector(`#optionsContainer${questionCount}`);
                const optionFieldsContainer = questionTemplateDiv.querySelector(`#optionFields${questionCount}`);
                const correctOptionSelect = optionsContainer.querySelector(`#correctOption${questionCount}`);
                const numOfOptionsInput = questionTemplateDiv.querySelector(`#numOfOptions${questionCount}`);

                correctOptionSelect.innerHTML = generateCorrectOptions(numOfOptionsInput.value);
                optionFieldsContainer.innerHTML = generateOptionFields(numOfOptionsInput.value, questionCount);

                numOfOptionsInput.addEventListener("input", () => {
                    correctOptionSelect.innerHTML = generateCorrectOptions(numOfOptionsInput.value);
                    optionFieldsContainer.innerHTML = generateOptionFields(numOfOptionsInput.value, questionCount);
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
                <label for="questionText${questionNumber}" style="color: indigo;">Question Text:</label>
                <input type="text" id="questionText${questionNumber}" name="questionText${questionNumber}" required style="width: 100%; padding: 8px; font-size: 25px;">
                <br>
                <label for="answerText${questionNumber}" style="color: indigo;">Answer:</label>
                <input type="text" id="answerText${questionNumber}" name="answerText${questionNumber}" required style="width: 100%; padding: 8px; font-size: 25px;">            `;
            case 2:
                return `
                <label for="beforeGap${questionNumber}" style="color: indigo;">Before Gap:</label>
                <input type="text" id="beforeGap${questionNumber}" name="beforeGap${questionNumber}" required style="width: 100%; padding: 8px; font-size: 25px;">
                <br>
                <label for="answer${questionNumber}" style="color: indigo;">Answer:</label>
                <input type="text" id="answer${questionNumber}" name="answer${questionNumber}" required style="width: 100%; padding: 8px; font-size: 25px;">
                <br>
                <label for="afterGap${questionNumber}" style="color: indigo;">After Gap:</label>
                <input type="text" id="afterGap${questionNumber}" name="afterGap${questionNumber}" required style="width: 100%; padding: 8px; font-size: 25px;">
            `;
            case 3:
                return `
                    <label for="questionText${questionNumber}" style="color: indigo;">Question Text:</label>
                    <input type="text" id="questionText${questionNumber}" name="questionText${questionNumber}" required style="width: 100%; padding: 8px; font-size: 25px;">
                    <br>
                    <label for="numOfOptions${questionNumber}" style="color: indigo;">Number of Options:</label>
                    <input type="number" id="numOfOptions${questionNumber}" name="numOfOptions${questionNumber}" required style="width: 100%; padding: 8px; font-size: 25px;">
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
                `;
            case 4:
                return `
                <label for="questionText${questionNumber}" style="color: indigo;">Image url:</label>
                <input type="text" id="questionText${questionNumber}" name="questionText${questionNumber}" required style="width: 100%; padding: 8px; font-size: 25px;">
                <br>
                <label for="answerText${questionNumber}" style="color: indigo;">Answer:</label>
                <input type="text" id="answerText${questionNumber}" name="answerText${questionNumber}" required style="width: 100%; padding: 8px; font-size: 25px;">                           `;
            default:
                return "";
        }
    }
    function generateOptionFields(numOfOptions, questionNumber) {
        let optionsHtml = '';

        for (let i = 1; i <= numOfOptions; i++) {
            optionsHtml += `
                <input type="text" id="option${questionNumber}_text${i}" name="option${questionNumber}_text${i}" placeholder="Option ${i}" style="width: 100%; padding: 8px; font-size: 16px;">
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



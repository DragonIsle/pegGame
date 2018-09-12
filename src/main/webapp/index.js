function findSolution(boardType) {
    setSolutionString("This may take some time. Please wait...");
    $.post("/solution", { boardType: boardType.toUpperCase() },
        function (data) {
            setSolutionString("Steps to win: \n" + data);
        }, 'text');
}

function setSolutionString(string) {
    document.getElementById("solutionString").innerHTML = string;
}

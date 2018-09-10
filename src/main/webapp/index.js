function findSolution(boardType) {
    setSolutionString("This may take some time. Please wait...");
    $.post("/solution", { boardType: boardType },
        function (data) {
            setSolutionString(data);
        }, 'text');
}

function setSolutionString(string) {
    document.getElementById("solutionString").innerHTML = string;
}

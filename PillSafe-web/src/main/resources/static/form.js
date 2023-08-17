
/*
[JS 요약 설명]
*/


/* [html 최초 로드 및 이벤트 상시 대기 실시] */
window.onload = function() {
    console.log("");
    console.log("[window onload] : [start]");
    console.log("");
};


/* [이벤트 함수 정의] */
function checkAnswer(){
    console.log("");
    console.log("[checkAnswer] : [start]");
    console.log("");

    // [체크 된 항목을 저장할 변수 선언]
    var noCheck = "";
    var result = "";

    // [그룹별 라디오 버튼이 정상적으로 선택 되었는지 확인]
    var one = $(":input:radio[name='qna_1_group']:checked").val();
    var two = $(":input:radio[name='qna_2_group']:checked").val();
    var three = $(":input:radio[name='qna_3_group']:checked").val();
    console.log("");
    console.log("[checkAnswer] : [answer check]");
    console.log("[one] : " + one);
    console.log("[two] : " + two);
    console.log("[three] : " + three);
    console.log("");

    // [체크 안한 항목이 있다면 함수 종료]
    var checkArray = [one, two, three, four, five];
    console.log("");
    console.log("[checkAnswer] : [checkArray]");
    console.log("[arr] : " + checkArray);
    console.log("");
    for(var i=0; i<checkArray.length; i++){
        if (checkArray[i] != null && checkArray[i] != "" && checkArray[i] != "undefined") {
            result = result + String(i+1) + "번[" +checkArray[i]+ "]  ";
        }
        else {
            noCheck = noCheck + String(i+1) + "번[No Check]  ";
        }
    }
    console.log("");
    console.log("[checkAnswer] : [check confirm]");
    console.log("[noCheck] : " + noCheck);
    console.log("[result] : " + result);
    console.log("");
    if(noCheck.length > 0){ //체크 안한 항목이 있을 경우
        //alert(noCheck);
        sweetAlert(4, "", noCheck);
    }
    else {
        sweetAlert(2, "", result);
    }
};


function sweetAlert(type, title, content){
    console.log("");
    console.log("[sweetAlert] : " + "[start] : " + type);
    console.log("");

    //타입 분기 처리 실시 (1=일반/2=성공/3=경고/4=실패/5=취소,확인 버튼)
    if(type == 1){
        swal({
            title: title,
            text: content,
            className : 'swal-wide' //커스텀 사이즈
        });
    }
    else if(type == 2){
        swal({
            title: title,
            text: content,
            className : 'swal-wide', //커스텀 사이즈
            icon: "success", //success, warning, error
            button: "확인"
        });
    }
    else if(type == 3){
        swal({
            title: title,
            text: content,
            className : 'swal-wide', //커스텀 사이즈
            icon: "warning", //success, warning, error
            button: "확인"
        });

    }
    else if(type == 4){
        swal({
            title: title,
            text: content,
            className : 'swal-wide', //커스텀 사이즈
            icon: "error", //success, warning, error
            button: "확인"
        });
    }
    else if(type == 5){
        swal({
            title: title,
            text: content,
            className : 'swal-wide', //커스텀 사이즈
            icon: "error", //success, warning, error
            buttons: {
                cancel : "취소",
                catch: {
                    text: "확인",
                    value: "catch"
                },
            },
        })
            .then((value) => {
                switch(value){
                    case "catch" :
                        sweetAlert(1, "Title", "확인 클릭");
                        break;
                }
            });

    }
};
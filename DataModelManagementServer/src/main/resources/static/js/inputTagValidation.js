// 단어 논리명은 띄어쓰기, '-', '/' 은 허용
function characterCheckForLogicalName(obj) {
	var regExp = /[\{\}\[\]\?.,;:|\)*~`!^\_+┼<>@\#$%&\'\"\\\(\=]/gi;
	
    if( regExp.test(obj.value) ){
		showAlert("특수문자는 띄어쓰기 , / , - 만 사용 가능합니다.");
        obj.value = obj.value.substring( 0 , obj.value.length - 1 ); // 입력한 특수문자 한자리 지움
    }
}

function characterCheck(obj){
    var regExp = /[\{\}\[\]\?.,;:|\)*~`!^\_+┼<>@\#$%&\'\"\\\(\=\s\-\/]/gi;

// 허용할 특수문자는 여기서 삭제하면 됨
// 지금은 띄어쓰기도 특수문자 처리됨 참고하셈
    if( regExp.test(obj.value) ){
		showAlert("특수문자는 입력하실수 없습니다.");
        obj.value = obj.value.substring( 0 , obj.value.length - 1 ); // 입력한 특수문자 한자리 지움
    }
}
// Hàm show dùng để ẩn hiện trình độ tiếng Nhật
function show() {
	// Khởi tạo biến x để bắt sự kiện khi onclick
	var x = document.getElementById("showLevelJapan");
	// Nếu trình độ đang không hiển thị thì set lại về hiển thị inline-table
	if(x.style.display == 'none'){
		x.style.display = 'inline-table';
		//  Ngược lại thì không cho hiển thị
	} else {
		x.style.display = 'none';
	}
	}

// Hàm thông báo khi xóa user

function showAlertDelete(messDelete, id) {
	alert(messDelete);
	var form = document.createElement("form");
	document.body.appendChild(form);
	form.method = "post";
	form.action = "DeleteUserInfor.do";
	var input = document.createElement("input");
	input.name = "id";
	input.value = id;
	input.type = 'hidden';
	
	form.appendChild(input);
	form.submit();
}
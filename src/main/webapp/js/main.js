/**
 * 
 */


/*const btn = document.querySelector('.btn-select');
const list = document.querySelector('.list-member');
btn.addEventListener('click', () => {
    btn.classList.toggle('on');
});
list.addEventListener('click', (event) => {
    if (event.target.nodeName === "BUTTON") {
        btn.innerText = event.target.innerText;
        btn.classList.remove('on');
    }
});*/

//클릭 시 글자색 변경
var liElements = document.getElementsByClassName("one");

function handleClick(e) {
  console.log(e.target);
  console.log(e.target.classList);

  if (e.target.classList.contains("clicked")) {
    e.target.classList.remove("clicked");
  } else {
    for (var i = 0; i < liElements.length; i++) {
      liElements[i].classList.remove("clicked");
    }
    e.target.classList.add("clicked");
  }
}

function init() {
  for (var i = 0; i < liElements.length; i++) {
    liElements[i].addEventListener("click", handleClick);
  }
}

init();

//클릭 시 글자 밑 선 색 변경

window.onload = function() {
  var one = document.getElementsByClassName("one");
  var linecolor =document.querySelector(".linecolor");
  console.log(linecolor);
  for(var i =0; i<one.length; i++) {
    one[i].onclick = function(event){
        
      if(event.target == one[0]){
        linecolor.style.left= '0%';
      }else if(event.target == one[1]){
        linecolor.style.left = '10%';
      }else if(event.target == one[2]){
        linecolor.style.left = '25%';
      }else if(event.target == one[3]){
        linecolor.style.left = '40.5%';
      }else if(event.target == one[4]){
        linecolor.style.left = '54.5%';
      }else if(event.target == one[5]){
        linecolor.style.left = '69.5%';
      }

    }
  }
}
//페이징
$(document).ready(function(){
		var $form = $("form[name=searchForm]");
		// 페이지 번호 클릭 시 검색결과 유지하기 위함
		$(".page-item > a").on("click", function(e){
			e.preventDefault();	// 이벤트 실행 방지, <a>의 기본 클릭 이벤트 막기 위함
			// 현재 페이지번호 설정
			$form.find("input[name=currPage]").val($(this).data("page"));
			$form.submit();
		});
		
		// 검색 버튼 클릭 시 페이지번호 1부터 시작하기 위함
		$form.find("input[type=submit]").on("click", function(e){
			e.preventDefault();
			$form.find("input[name=currPage]").val(1);
			$form.submit();
		})
	});



/*const open = () => {
  document.querySelector(".modal").classList.remove("hidden");
}

const close = () => {
  document.querySelector(".modal").classList.add("hidden");
}

document.querySelector(".openBtn").addEventListener("click", open);
document.querySelector(".closeBtn").addEventListener("click", close);
document.querySelector(".bg").addEventListener("click", close);
*/
//좋아요
var cnt =1;
function changeHeartImage(){
	var img =document.querySelectorAll("img.like");
	if(cnt%2==0){
		img.src="images/color_heart.svg";
		
	}else {
		img.src="images/like.png"
	}
	cnt++;
}

$(document).ready(function() {
	var isToggle = false;
	$(".mainList img.like").on("click", function(e){
		e.preventDefault();
		
		if(!isToggle) {
			$(this).attr("src", "images/color_heart.png");
			isToggle = true;		
		} else {
			$(this).attr("src", "images/like.png");
			isToggle = false;	
		}
	});
});
  


  $(document).ready(function() {
    // 카테고리 클릭 이벤트 처리
    $(".one").click(function() {
      var categoryId = $(this).text(); // 클릭한 카테고리의 ID 가져오기
		
      // AJAX 요청 보내기
      $.ajax({
        url: "CategoryServlet", // AJAX 요청을 처리할 JSP 페이지 경로
        type: "post",
        data: { category: categoryId }, // 카테고리 ID를 파라미터로 전달
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "html", // 받을 데이터 형식
        success: function(data) {
          // 서버로부터 받은 HTML 데이터를 메인 페이지의 콘텐츠 영역에 추가
          console.log(data);
          $(".mainList").html(data);
        },
        error: function() {
          alert("게시물을 불러오는 데 실패했습니다.");
        }
      });
    });
  });

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
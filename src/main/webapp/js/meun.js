/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function meunFunction() {
    document.getElementById("meun_myDropdown").classList.toggle("meun_show");
  }
  
  // Close the dropdown menu if the user clicks outside of it
  window.onclick = function(event) {
    if (!event.target.matches('.meun_dropbtn, .meun_dropbtn > img')) {
      var meun_dropdowns = document.getElementsByClassName("meun_dropdown-content");
      var i;
      for (i = 0; i < meun_dropdowns.length; i++) {
        var openDropdown = meun_dropdowns[i];
        if (openDropdown.classList.contains('meun_show')) {
          openDropdown.classList.remove('meun_show');
        }
      }
    }
  }
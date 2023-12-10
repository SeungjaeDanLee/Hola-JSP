function removeImage() {
    // Replace the 'images/mark.png' with the URL of your default image
    document.getElementById('userImg').src = 'images/mark.png';
    document.getElementById('imgFile').value = ''; // Clear the file input
  }

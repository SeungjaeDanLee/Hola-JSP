function submitForms() {
  const form1Data = new FormData(document.getElementById('form1'));
  const form2Data = new FormData(document.getElementById('form2'));
  const form3Data = new FormData(document.getElementById('form3'));


  fetch('UploadActionServlet', {
    method: 'POST',
    body: form1Data, // 폼1 데이터 전송
  })
    .then(response => response.text())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));

  fetch('RemoveImageServlet', {
    method: 'POST',
    body: form2Data, // 폼2 데이터 전송
  })
    .then(response => response.text())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));
    
  fetch('UpdateNickNameServlet', {
    method: 'POST',
    body: form3Data, // 폼3 데이터 전송
  })
    .then(response => response.text())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));  
}




 

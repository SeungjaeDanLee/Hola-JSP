const resultDiv = document.getElementById('result');
		
		function getselect(selectElement) {
		  const selectedOption = selectElement.value;
		  const resultText = resultDiv.textContent;
		
		  if (!resultText.includes(selectedOption)) {
			resultDiv.textContent += `${selectedOption}, `;
		  }
		}
		
		function clearSelection() {
		  resultDiv.textContent = '';
		}
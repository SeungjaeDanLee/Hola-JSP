function performMemberRemoval() {
        fetch('removeMember', { method: 'POST' }) // This will make a POST request to the /removeMember URL mapped to the RemoveMemberServlet.
            .then(response => {
                if (response.ok) {
                    // Redirect the user to the main page or any other appropriate page after removal.
                    window.location.href = 'main.jsp';
                } else {
                    // Handle any errors that might occur during the removal process.
                    console.error('Member removal failed.');
                }
            })
            .catch(error => {
                console.error('An error occurred:', error);
            });
    }

    // Attach the performMemberRemoval function to the "네, 삭제할게요" button in the modal window.
    document.getElementById('mButton2').addEventListener('click', performMemberRemoval);


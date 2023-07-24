const apiEndpoint = 'https://your-api-gateway-endpoint-url'; // Replace with your API Gateway endpoint

document.getElementById('uploadForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const fileInput = document.getElementById('imageInput');
    const file = fileInput.files[0];

    if (file) {
        const formData = new FormData();
        formData.append('image', file);

        try {
            const response = await fetch(`${apiEndpoint}/upload`, {
                method: 'POST',
                body: formData,
            });

            if (response.ok) {
                alert('Image uploaded successfully!');
                // Refresh the image gallery
                loadImages();
            } else {
                alert('Failed to upload image.');
            }
        } catch (error) {
            console.error('Error uploading image:', error);
            alert('Error uploading image. Please try again later.');
        }
    }
});

async function loadImages() {
    try {
        const response = await fetch(`${apiEndpoint}/images`);
        const images = await response.json();
        const imageGallery = document.getElementById('imageGallery');
        imageGallery.innerHTML = '';

        images.forEach((image) => {
            const imgElement = document.createElement('img');
            imgElement.src = image.url;
            imgElement.alt = image.name;
            imageGallery.appendChild(imgElement);
        });
    } catch (error) {
        console.error('Error fetching images:', error);
        alert('Error fetching images. Please try again later.');
    }
}

// Load images when the page loads
loadImages();

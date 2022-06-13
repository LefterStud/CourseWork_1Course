const $info_shield = document.querySelector('.info_shield');
const $liveSearch = document.querySelector('.byText');
const $countOfBanners = document.querySelector('.bannerCount');
const $searchByText = document.getElementById('liveSearch');
const $selectKindOfSport = document.getElementById('winnersList');
const $selectCountry = document.querySelector('.winnersByCountry');
const $winnersBySport = document.querySelector('.winnersBySport');
const $liveSearchField = document.querySelector('.liveSearchField');
const $butSearchWinnersByCountry = document.querySelector('.winnersByCountryBut');
const $resetBut = document.querySelector('.reset');
const $countryMedalsInfo = document.querySelector('.info');
const $medalsButt = document.querySelector('.medalsButt');
const $sortingButt = document.querySelector('.sortingButt');
const $countryMedals = document.querySelector('.countryMedals');
const $sorting = document.querySelector('.sorting');
const $addModal = document.querySelector('.qButAdd');
const $cancelModal = document.querySelector('.qButCancel');
const $darkBlock = document.querySelector('.dark');
const $addPerson = document.querySelector('.addPerson');
const $deletePerson = document.querySelector('.deletePerson');
const $yesConfirm = document.querySelector('.sButYes');
const $noConfirm = document.querySelector('.sButNo');
const $confirmDarkBlock = document.querySelector('.darkSure');
const $confirmPass = document.querySelector('.confirm');
const $passField = document.querySelector('.passField');
const $passDark = document.querySelector('.darkPass');
const $infoPass = document.querySelector('.inP');
const $darkDelete = document.querySelector('.darkDelete');
const $dSurname = document.querySelector('.dSurname');
const $deleteBut = document.querySelector('.deleteBut');
const $dCancel = document.querySelector('.dCancel');
const $body = document.querySelector('.mainBody');
const $darkEdit = document.querySelector('.darkEdit');
const $confirmEdit = document.querySelector('.eButAdd');
const $cancelEdit = document.querySelector('.eButCancel');
const $cancelPass = document.querySelector('.cancelPass');

const $qName = document.querySelector('.qName');
const $qSurname = document.querySelector('.qSurname');
const $qPatronymic = document.querySelector('.qPatronymic');
const $qCountry = document.querySelector('.qCountry');
const $qkindOfSport = document.querySelector('.qkindOfSport');
const $qResult = document.querySelector('.qShownResult');
const $eName = document.querySelector('.eName');
const $eSurname = document.querySelector('.eSurname');
const $ePatronymic = document.querySelector('.ePatronymic');
const $eCountry = document.querySelector('.eCountry');
const $ekindOfSport = document.querySelector('.ekindOfSport');
const $eResult = document.querySelector('.eShownResult');


let filterdPersons;
//Зчитування з АРІ
let persons = null;
function reGetInfo() {
    fetch("http://localhost:8080/back?info")
        .then(function (responce) {
            return responce.json();
        })
        .then(function (data) {
            persons = data.list;
            medalsCounter(persons);
            getInfo(persons);
        });
};
reGetInfo();

function clearFields() {
    $dSurname.value = '';
    $qName.value = '';
    $qSurname.value = '';
    $qPatronymic.value = '';
    $qCountry.value = '';
    $qkindOfSport.value = 'none';
    $qResult.value = '';
    $eName.value = '';
    $eSurname.value = '';
    $ePatronymic.value = '';
    $eCountry.value = '';
    $ekindOfSport.value = 'none';
    $eResult.value = '';
    listChange = '';
    $passField.value = '';
};

//Перевірка коректності доних в формі для додавання об'єкту
function isAddCorrect() {
    isCorrect = true;
    if (($qName.value == ' ') || (!(/^[A-Za-z]+$/i).test($qName.value))) {
        isCorrect = false;
    }
    if (($qSurname.value == ' ') || (!(/^[A-Za-z]+$/i).test($qSurname.value))) {
        isCorrect = false;
    }
    if (($qPatronymic.value == ' ') || (!(/^[A-Za-z]+$/i).test($qPatronymic.value))) {
        isCorrect = false;
    }
    if (!(/^[\- A-Za-z]+$/i).test($qCountry.value)) {
        isCorrect = false;
    }
    if (($qkindOfSport.value == 'none')) {
        isCorrect = false;
    }
    if (($qResult.value == '')||($qResult.value < 0)) {
        isCorrect = false;
    }
    return isCorrect;
}

//Перевірка коректності доних в формі для змінення об'єкту
function isEditCorrect() {
    isCorrect = true;
    if (($eName.value == ' ') || (!(/^[A-Za-z]+$/i).test($eName.value))) {
        isCorrect = false;
    }
    if (($eSurname.value == ' ') || (!(/^[A-Za-z]+$/i).test($eSurname.value))) {
        isCorrect = false;
    }
    if (($ePatronymic.value == ' ') || (!(/^[A-Za-z]+$/i).test($ePatronymic.value))) {
        isCorrect = false;
    }
    if ((!(/^[\- A-Za-z]+$/i).test($eCountry.value))) {
        isCorrect = false;
    }
    if (($ekindOfSport.value == 'none')) {
        isCorrect = false;
    }
    if (($eResult.value == '')||($qResult.value < 0)) {
        isCorrect = false;
    }
    return isCorrect;
}

//Перевірка коректності прізвища та існування об'єкта з даним прізьвиськом
function isDSurnameCorrect() {
    isCorrect = true;
    if (($dSurname.value == ' ') || (!(/^[A-Za-z]+$/i).test($dSurname.value))) {
        isCorrect = false;
    }
    return isCorrect;
}


//Відкриття та закриття довідки про медалі
let isActiveMedalsButt = false;
$medalsButt.addEventListener('click', function () {
    if (isActiveMedalsButt == true) {
        $medalsButt.style.backgroundImage = "url('../CourseWork_1Course_WEB/img/open_arrow.png')";
        $countryMedals.style.right = "-340px";
        $medalsButt.style.right = "0px"
    }
    if (isActiveMedalsButt == false) {
        $medalsButt.style.backgroundImage = "url('../CourseWork_1Course_WEB/img/close_arrow.png')";
        $countryMedals.style.right = "0px";
        $medalsButt.style.right = "339px"
    }
    isActiveMedalsButt = !isActiveMedalsButt;
});
$countryMedals.addEventListener('click', function () {
    $medalsButt.style.backgroundImage = "url('../CourseWork_1Course_WEB/img/open_arrow.png')";
    $countryMedals.style.right = "-340px";
    $medalsButt.style.right = "0px"
});

//Відкриття та закриття вікна пошуку в адаптивній версії
let isActiveSortingButt = false;
$sortingButt.addEventListener('click', function () {
    if (isActiveSortingButt == true) {
        $sorting.style.left = "-330px";
        $sortingButt.style.left = "0px"
    }
    if (isActiveSortingButt == false) {
        $sorting.style.left = "0px";
        $sortingButt.style.left = "330px"
    }
    isActiveSortingButt = !isActiveSortingButt;
});

//Редагування об'єктів
let selectedBannerNumber;
$info_shield.addEventListener('dblclick', function (e) {
    if (e.target.classList.contains('person_banner') || e.target.classList.contains('person_bannerP')) {
        listChange = 'edition';
        selectedBannerNumber = e.target.getAttribute('data-index') - 1;
        $passDark.style.opacity = "1";
        $passDark.style.visibility = "visible";
        $body.style.overflow = "hidden";
        $eName.value = persons[selectedBannerNumber].name;
        $eSurname.value = persons[selectedBannerNumber].surname;
        $ePatronymic.value = persons[selectedBannerNumber].patronymic;
        $eCountry.value = persons[selectedBannerNumber].country;
        $ekindOfSport.value = persons[selectedBannerNumber].kindOfSport;
        $eResult.value = persons[selectedBannerNumber].shownResult;
    }
});

//Редагування об'єктів для адаптивної версії
var e1;
var e2;
$info_shield.addEventListener('touchstart', function (e) {
    if (e.target.classList.contains('person_banner') || e.target.classList.contains('person_bannerP')) {
        e1 = new Date();
    }
});
$info_shield.addEventListener('touchend', function (e) {
    if (e.target.classList.contains('person_banner') || e.target.classList.contains('person_bannerP')) {
        e2 = new Date();
        if (((e2 - e1) / 1000) > 0.6) {
            listChange = 'edition';
            selectedBannerNumber = e.target.getAttribute('data-index') - 1;
            $passDark.style.opacity = "1";
            $passDark.style.visibility = "visible";
            $body.style.overflow = "hidden";
            $eName.value = persons[selectedBannerNumber].name;
            $eSurname.value = persons[selectedBannerNumber].surname;
            $ePatronymic.value = persons[selectedBannerNumber].patronymic;
            $eCountry.value = persons[selectedBannerNumber].country;
            $ekindOfSport.value = persons[selectedBannerNumber].kindOfSport;
            $eResult.value = persons[selectedBannerNumber].shownResult;
        }
        e1 = null;
        e2 = null;
    }
});

//Підтвердження редагування
$confirmEdit.addEventListener("click", function () {
    if (isEditCorrect()) {
        let changedAthletes = {
            name: $eName.value,
            surname: $eSurname.value,
            patronymic: $ePatronymic.value,
            country: $eCountry.value,
            kindOfSport: $ekindOfSport.value,
            registrationNumber: selectedBannerNumber + 1,
            shownResult: $eResult.value,
            ratingPlace: 0
        }
        fetch("http://localhost:8080/back?changeAthlete", {
            method: 'POST',
            body: JSON.stringify(changedAthletes),
        }).then(response =>
            response.json().then(data => ({
                data: data,
                status: response.status
            })).then(res => {
                if (JSON.stringify(res.data) == '{"Changing":"Changed"}') {
                    alert("Edited!")
                } else {
                    alert("Try again")
                }
            }));
        $body.style.overflow = "visible";
        $darkEdit.style.opacity = "0";
        $darkEdit.style.visibility = "hidden"
        clearFields();
        alert('Successful!');
        reGetInfo();
    } else {
        alert('Incorrect data!');
    }
});

//Відмінна редагування
$cancelEdit.addEventListener("click", function () {
    $confirmDarkBlock.style.opacity = "1";
    $confirmDarkBlock.style.visibility = "visible";
});

//Додавання об'єктів
$addPerson.addEventListener("click", function () {
    $darkBlock.style.opacity = "1";
    $darkBlock.style.visibility = "visible";
    $body.style.overflow = "hidden";
});

//Видалення об'єктів
$deletePerson.addEventListener("click", function () {
    $darkDelete.style.opacity = "1";
    $darkDelete.style.visibility = "visible";
    $body.style.overflow = "hidden";
});

//Підтвердження додавання об'єкту
$addModal.addEventListener("click", function () {
    if (isAddCorrect()) {
        listChange = 'adding';
        $passDark.style.opacity = "1";
        $passDark.style.visibility = "visible";
    } else {
        alert('Incorrect data!');
    }
});

//Відміна додавання об'єкту
$cancelModal.addEventListener("click", function () {
    $confirmDarkBlock.style.opacity = "1";
    $confirmDarkBlock.style.visibility = "visible";
});

//Підтвердження видалення об'єкту
$deleteBut.addEventListener("click", function () {
    if (isDSurnameCorrect()) {
        let deleteBySurname = $dSurname.value.charAt(0).toUpperCase() + $dSurname.value.substr(1);
        fetch("http://localhost:8080/back?isAthleteExist", {
            method: 'POST',
            body: deleteBySurname,
        }).then(response =>
            response.json().then(data => ({
                data: data,
                status: response.status
            })).then(res => {
                if (JSON.stringify(res.data) == '{"Person":"Exist"}') {
                    listChange = 'deletion';
                    $passDark.style.opacity = "1";
                    $passDark.style.visibility = "visible";
                } else {
                    alert("Athlete with this surname doesn't exist!");
                }
            }));
    } else {
        alert('Incorrect data!');
    }
});

//Відміна видалення
$dCancel.addEventListener("click", function () {
    $confirmDarkBlock.style.opacity = "1";
    $confirmDarkBlock.style.visibility = "visible";
});

//Підвтердження (так)
$yesConfirm.addEventListener("click", function () {
    $darkBlock.style.opacity = "0";
    $darkBlock.style.visibility = "hidden";
    $darkDelete.style.opacity = "0";
    $darkDelete.style.visibility = "hidden";
    $confirmDarkBlock.style.opacity = "0";
    $confirmDarkBlock.style.visibility = "hidden";
    $darkEdit.style.opacity = "0";
    $darkEdit.style.visibility = "hidden"
    $body.style.overflow = "visible";
    clearFields();
    alert('Failed!');
});

//Підтвердження (ні)
$noConfirm.addEventListener("click", function () {
    $confirmDarkBlock.style.opacity = "0";
    $confirmDarkBlock.style.visibility = "hidden";
});

//Функція додавання
function adding() {
    addedPerson = {
        name: $qName.value,
        surname: $qSurname.value,
        patronymic: $qPatronymic.value,
        country: $qCountry.value,
        kindOfSport: $qkindOfSport.value,
        registrationNumber: 0,
        shownResult: $qResult.value,
        ratingPlace: 0
    }
    fetch("http://localhost:8080/back?addAthlete", {
        method: 'POST',
        body: JSON.stringify(addedPerson),
    }).then(response =>
        response.json().then(data => ({
            data: data,
            status: response.status
        })).then(res => {
            if (JSON.stringify(res.data) == '{"Adding":"Added"}') {
                alert("Added!")
            } else {
                alert("Try again")
            }
        }));
    alert('Successful!');
};

//Функція видалення 
function deletion() {
    let deleteBySurname = $dSurname.value.charAt(0).toUpperCase() + $dSurname.value.substr(1);
    fetch("http://localhost:8080/back?deleteAthlete", {
        method: 'POST',
        body: deleteBySurname,
    }).then(response =>
        response.json().then(data => ({
            data: data,
            status: response.status
        })).then(res => {
            if (JSON.stringify(res.data) == '{"Deletion":"Deleted"}') {
                alert("Deleted!")
            } else {
                alert("Try again!")
            }
        }));
    alert('Successful!');
};


//Пароль додавання/видалення/редагування об'єкту
let listChange = '';
$confirmPass.addEventListener("click", function () {
    let pValue = $passField.value;
    fetch("http://localhost:8080/back?login", {
        method: 'POST',
        body: pValue,
    }).then(response =>
        response.json().then(data => ({
            data: data,
            status: response.status
        })).then(res => {
            if (JSON.stringify(res.data) == 'true') {
                if (listChange == 'adding') {
                    adding();
                    clearFields();
                    reGetInfo(persons);
                }
                if (listChange == 'deletion') {

                    deletion();
                    clearFields();
                    reGetInfo(persons);
                };
                if (listChange == 'edition') {
                    $darkEdit.style.opacity = "1";
                    $darkEdit.style.visibility = "visible";
                    alert('Successful!');
                };
                $passDark.style.opacity = "0";
                $passDark.style.visibility = "hidden";
                $darkBlock.style.opacity = "0";
                $darkBlock.style.visibility = "hidden";
                $darkDelete.style.opacity = "0";
                $darkDelete.style.visibility = "hidden";
                $body.style.overflow = "visible";
                $infoPass.style.visibility = "hidden";
            } else {
                $infoPass.style.visibility = "visible";
            }
        }));
});

//Відміна вводу пароля
$cancelPass.addEventListener('click', function () {
    $passDark.style.opacity = "0";
    $passDark.style.visibility = "hidden"
    $passField.value = '';
    $infoPass.style.visibility = "hidden";
    if (listChange == 'edition') {
        $body.style.overflow = "visible";
    }
});

//Підрахунок медалей 
function medalsCounter(list) {
    let medals = '<p>Medals of countries</p><table><tr><td>Country</td><td class="goldM">Gold</td><td class="silverM">Silver</td><td class="bronzeM">Bronze</td></tr>';
    let goldMedals = 0;
    let silverMedals = 0;
    let bronzeMedals = 0;
    list.sort((a, b) => a.country > b.country ? 1 : -1);
    let tempCountry = list[0].country;
    for (let i = 0; i < list.length; i++) {
        if (list[i].country == tempCountry) {
            switch (list[i].ratingPlace) {
                case 1: goldMedals++; break;
                case 2: silverMedals++; break;
                case 3: bronzeMedals++;
            }
        } else {
            medals += '<tr><td>' + tempCountry + '</td><td class="goldM">' + goldMedals + '</td><td class="silverM">' + silverMedals + '</td><td class="bronzeM">' + bronzeMedals + '</td></tr>';
            goldMedals = 0;
            silverMedals = 0;
            bronzeMedals = 0;
            tempCountry = list[i].country;
            switch (list[i].ratingPlace) {
                case 1: goldMedals++; break;
                case 2: silverMedals++; break;
                case 3: bronzeMedals++;
            }
        }
        if (i == list.length - 1) {
            medals += '<tr><td>' + tempCountry + '</td><td class="goldM">' + goldMedals + '</td><td class="silverM">' + silverMedals + '</td><td class="bronzeM">' + bronzeMedals + '</td></tr>';
        }
    }
    medals += '</table>';
    $countryMedalsInfo.innerHTML = medals;
    list.sort((a, b) => a.registrationNumber > b.registrationNumber ? 1 : -1);
}

//Показ зчитанних данних
function getInfo(list) {
    let temp = '';
    if (list.length) {
        for (let i = 0; i < list.length; i++) {
            temp += '<div class="person_banner"data-index="' + list[i].registrationNumber + '"><p class="person_bannerP" data-index="' + list[i].registrationNumber + '">' + list[i].name + '<br>' + list[i].surname + '<br>' + list[i].patronymic + '<br>' + list[i].country + '<br>' + list[i].kindOfSport + '<br>Reg. number: ' + list[i].registrationNumber + '<br>Result: ' + list[i].shownResult + '<br>Rating place: ' + list[i].ratingPlace + '</p></div>';
        }
    } else {
        temp += '<h1 class="NF">Athletes not found!</h1>';
    }
    list.sort((a, b) => a.registrationNumber > b.registrationNumber ? 1 : -1);
    $info_shield.innerHTML = temp;
    $countOfBanners.innerHTML = "Displayed athletes: " + list.length;
}

//Відмінити сортування
$resetBut.addEventListener('click', function () {
    getInfo(persons);
    $liveSearch.value = '';
    $selectCountry.value = '';
    $winnersBySport.value = 'none';
    $liveSearchField.value = 'none';
});

//Живий пошук по вибраному полю та ключовому слову
$searchByText.addEventListener('change', function () {
    let searchingValue = $searchByText.elements["liveSearch"].value;
    $liveSearch.addEventListener('input', function () {
        let query = this.value.toString().toLowerCase();
        let filterdPersons = persons.filter(function (el) {
            return ~el[searchingValue].toString().toLowerCase().indexOf(query);
        });
        getInfo(filterdPersons);
    });
});

//Видача призерів по вибраному виду спорту
$selectKindOfSport.addEventListener('change', function () {
    let winnersSport = $selectKindOfSport.elements["winnersBySport"].value;
    let filterdPersons = persons.filter(function (el) {
        if (el.ratingPlace < 4) {
            return ~el.kindOfSport.indexOf(winnersSport);
        }
    });
    filterdPersons.sort((a, b) => a.ratingPlace > b.ratingPlace ? 1 : -1);
    getInfo(filterdPersons);
});

//Видача призерів про країні
$butSearchWinnersByCountry.addEventListener('click', function () {
    let query = $selectCountry.value.toLowerCase();
    let filterdPersons = persons.filter(function (el) {
        if (el.ratingPlace < 4) {
            return ~el.country.toLowerCase().indexOf(query);
        }
    });
    filterdPersons.sort((a, b) => a.ratingPlace > b.ratingPlace ? 1 : -1);
    getInfo(filterdPersons);
});

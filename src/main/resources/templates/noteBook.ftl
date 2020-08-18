<#import "/spring.ftl" as spring/>
<#import "parts/common.ftl" as c>

<@c.page>

<div class="alert alert-primary" role="alert">
    <br>
    <ul class="button-bar">
        <#--    <div class="button-bar">-->
        <button type="button" class="btn btn-light"><a href="?lang=ru">Русский</a></button>
        <button type="button" class="btn btn-light"><a href="?lang=en">English</a></button>
        <#--    </div>-->
    </ul>
</div>


<div class="alert alert-dark" role="alert">
    <p><strong>Требования к проекту</strong></p>
    <div class="alert alert-success" role="alert">
        1. Должен быть веб интерфейс
        <br>2. Должна быть возможность создать заметку. У заметки есть заголовок, текст, время и дата создания,
        время и
        дата последнего редактирования.
        <br>3. Должна быть возможность отредактировать существующую заметку.
        <br>4. Должна быть возможность удалить существующую заметку.
    </div>
    <div class="alert alert-warning" role="alert">
        5. Должна быть прикручена локализация приложения на разные языки.
        <br>6. Должна быть возможность экспортировать/импортировать заметку в xml/json/word.
        <br>7. Заметка должна хранить всю историю изменений.
        <br>8. Заметка должна принадлежать определенному пользователю и каждый пользователь может видеть только свои
        заметки.
    </div>
    <div class="alert alert-danger" role="alert">
        9. Пользователь может дать другому пользователю права на
        просмотр и права на редактирование его заметок.
        <br>10. Должна быть реализована возможность одновременного
        совместного редактирования заметок, как в гугл документах.
    </div>
    </@c.page>

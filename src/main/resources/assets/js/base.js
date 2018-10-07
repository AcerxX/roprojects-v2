(function ($) {
    'use strict';
    $(function () {
        var sidebar = $('.sidebar');

        //Add active class to nav-link based on url dynamically
        //Active class can be hard coded directly in html file also as required
        var current = location.pathname.split("/").slice(-1)[0].replace(/^\/|\/$/g, '');
        $('.nav li a', sidebar).each(function () {
            var $this = $(this);
            if (current === "") {
                //for root url
                if ($this.attr('href').indexOf("index.html") !== -1) {
                    $(this).parents('.nav-item').last().addClass('active');
                    if ($(this).parents('.sub-menu').length) {
                        $(this).closest('.collapse').addClass('show');
                        $(this).addClass('active');
                    }
                }
            } else {
                //for other url
                if ($this.attr('href').indexOf(current) !== -1) {
                    $(this).parents('.nav-item').last().addClass('active');
                    if ($(this).parents('.sub-menu').length) {
                        $(this).closest('.collapse').addClass('show');
                        $(this).addClass('active');
                    }
                }
            }
        });

        //Close other submenu in sidebar on opening any

        sidebar.on('show.bs.collapse', '.collapse', function () {
            sidebar.find('.collapse.show').collapse('hide');
        });


        //Change sidebar and content-wrapper height
        applyStyles();

        function applyStyles() {
            //Applying perfect scrollbar
            if ($('.scroll-container').length) {
                const ScrollContainer = new PerfectScrollbar('.scroll-container');
            }
        }

        //checkbox and radios
        $(".form-check label,.form-radio label").append('<i class="input-helper"></i>');


        $(".purchace-popup .popup-dismiss").on("click", function () {
            $(".purchace-popup").slideToggle();
        });
    });
})(jQuery);

$.ajaxSetup({
    headers: {
        'X-CSRF-Token': $('input[name="_csrf"]').val()
    }
});

$.fn.selectOptionWithText = function selectOptionWithText(targetText) {
    return this.each(function () {
        let $selectElement, $options, $targetOption;

        $selectElement = $(this);
        $options = $selectElement.find('option');
        $targetOption = $options.filter(
            function () {
                return $(this).text() === targetText
            }
        );

        $targetOption.prop('selected', true);
    });
};

$.fn.filterByText = function (textbox, selectSingleMatch) {
    return this.each(function () {
        var select = this;
        var options = [];
        $(select).find('option').each(function () {
            options.push({value: $(this).val(), text: $(this).text()});
        });
        $(select).data('options', options);
        $(textbox).bind('change keyup', function () {
            var options = $(select).empty().scrollTop(0).data('options');
            var search = $.trim($(this).val());
            var regex = new RegExp(search, 'gi');

            $.each(options, function (i) {
                var option = options[i];
                if (option.text.match(regex) !== null) {
                    $(select).append(
                        $('<option>').text(option.text).val(option.value)
                    );
                }
            });
            if (selectSingleMatch === true &&
                $(select).children().length === 1) {
                $(select).children().get(0).selected = true;
            }
        });
    });
};
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="<?php echo str_replace('_', '-', osc_current_user_locale()) ; ?>">
    <head>
        <?php osc_current_web_theme_path('head.php') ; ?>
        <meta name="robots" content="noindex, nofollow" />
        <meta name="googlebot" content="noindex, nofollow" />
    </head>
    <body>
        <div class="containerbg">
            <div class="container">
                <?php osc_current_web_theme_path('header.php') ; ?>
                <div class="content user_account">
                    <h1>
                        <strong><?php _e('User account manager', 'bcute') ; ?></strong>
                    </h1>
                    <div id="sidebar">
                        <?php echo osc_private_user_menu() ; ?>
                    </div>
                    <div id="main" class="modify_profile">
                        <h2><?php _e('Update your profile', 'bcute') ; ?></h2>
                        <?php UserForm::location_javascript(); ?>
                        <form action="<?php echo osc_base_url(true) ; ?>" method="post">
                            <input type="hidden" name="page" value="user" />
                            <input type="hidden" name="action" value="profile_post" />
                            <fieldset>
                                <div class="row">
                                    <label for="name"><?php _e('Name', 'bcute') ; ?></label>
                                    <?php UserForm::name_text(osc_user()) ; ?>
                                </div>
                                <div class="row">
                                    <label for="email"><?php _e('E-mail', 'bcute') ; ?></label>
                                    <span class="update">
                                        <?php echo osc_user_email() ; ?><br />
                                        <a href="<?php echo osc_change_user_email_url() ; ?>"><?php _e('Modify e-mail', 'bcute') ; ?></a> <a href="<?php echo osc_change_user_password_url() ; ?>" ><?php _e('Modify password', 'bcute') ; ?></a>
                                    </span>
                                </div>
                                <div class="row">
                                    <label for="user_type"><?php _e('User type', 'bcute') ; ?></label>
                                    <?php UserForm::is_company_select(osc_user()) ; ?>
                                </div>
                                <div class="row">
                                    <label for="phoneMobile"><?php _e('Cell phone', 'bcute') ; ?></label>
                                    <?php UserForm::mobile_text(osc_user()) ; ?>
                                </div>
                                <div class="row">
                                    <label for="phoneLand"><?php _e('Phone', 'bcute') ; ?></label>
                                    <?php UserForm::phone_land_text(osc_user()) ; ?>
                                </div>
                                <div class="row">
                                    <label for="country"><?php _e('Country', 'bcute') ; ?> *</label>
                                    <?php UserForm::country_select(osc_get_countries(), osc_user()) ; ?>
                                </div>
                                <div class="row">
                                    <label for="region"><?php _e('Region', 'bcute') ; ?> *</label>
                                    <?php UserForm::region_select(osc_get_regions(), osc_user()) ; ?>
                                </div>
                                <div class="row">
                                    <label for="city"><?php _e('City', 'bcute') ; ?> *</label>
                                    <?php UserForm::city_select(osc_get_cities(), osc_user()) ; ?>
                                </div>
                                <div class="row">
                                    <label for="city_area"><?php _e('City area', 'bcute') ; ?></label>
                                    <?php UserForm::city_area_text(osc_user()) ; ?>
                                </div>
                                <div class="row">
                                    <label for="address"><?php _e('Address', 'bcute') ; ?></label>
                                    <?php UserForm::address_text(osc_user()) ; ?>
                                </div>
                                <div class="row">
                                    <label for="webSite"><?php _e('Website', 'bcute') ; ?></label>
                                    <?php UserForm::website_text(osc_user()) ; ?>
                                </div>
                                <div class="row">
                                    <button type="submit"><?php _e('Update', 'bcute') ; ?></button>
                                </div>
                                <?php osc_run_hook('user_form') ; ?>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
            <?php osc_current_web_theme_path('footer.php') ; ?>
        </div>
        <?php osc_show_flash_message() ; ?>
        <?php osc_run_hook('footer') ; ?>
    </body>
</html>
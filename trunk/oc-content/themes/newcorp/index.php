<?php
    /*
     *      OSCLass – software for creating and publishing online classified
     *                           advertising platforms
     *
     *                        Copyright (C) 2010 OSCLASS
     *
     *       This program is free software: you can redistribute it and/or
     *     modify it under the terms of the GNU Affero General Public License
     *     as published by the Free Software Foundation, either version 3 of
     *            the License, or (at your option) any later version.
     *
     *     This program is distributed in the hope that it will be useful, but
     *         WITHOUT ANY WARRANTY; without even the implied warranty of
     *        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     *             GNU Affero General Public License for more details.
     *
     *      You should have received a copy of the GNU Affero General Public
     * License along with this program.  If not, see <http://www.gnu.org/licenses/>.
     */

    function newcorp_theme_info() {
        $theme = array(
                'name'         => 'OSClass NewCorp Theme'
                ,'version'     => '2.1'
                ,'description' => 'This is the OSClass theme for a job board.'
                ,'author_name' => 'OSClass Team'
                ,'author_url'  => 'http://osclass.org'
                ,'locations'   => array('header', 'categories')
            );

        return $theme ;
    }

?>

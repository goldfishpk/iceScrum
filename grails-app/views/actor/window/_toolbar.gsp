%{--
- Copyright (c) 2010 iceScrum Technologies.
-
- This file is part of iceScrum.
-
- iceScrum is free software: you can redistribute it and/or modify
- it under the terms of the GNU Affero General Public License as published by
- the Free Software Foundation, either version 3 of the License.
-
- iceScrum is distributed in the hope that it will be useful,
- but WITHOUT ANY WARRANTY; without even the implied warranty of
- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
- GNU General Public License for more details.
-
- You should have received a copy of the GNU Affero General Public License
- along with iceScrum.  If not, see <http://www.gnu.org/licenses/>.
-
- Authors:
-
- Vincent Barrier (vbarrier@kagilum.com)
- Damien vitrac (damien@oocube.com)
- Manuarii Stein (manuarii.stein@icescrum.com)
- Nicolas Noullet (nnoullet@kagilum.com)
--}%

<is:shortcut key="ctrl+f" callback="jQuery('#search-ui').mouseover();" scope="${controllerName}"/>
<is:shortcut key="esc" callback="jQuery('#search-ui').mouseout();" scope="${controllerName}" listenOn="'#autoCmpTxt'"/>

<g:if test="${request.productOwner}">
    <li class="navigation-item button-ico button-add">
        <a class="tool-button button-n"
           href="#${controllerName}/add"
           data-is-shortcut
           data-is-shortcut-key="ctrl+n"
           data-is-shortcut-on="#window-id-${controllerName}"
           title="${message(code:'is.ui.actor.toolbar.alt.new')}"
           alt="${message(code:'is.ui.actor.toolbar.alt.new')}">
                <span class="start"></span>
                <span class="content">
                    <span class="ico"></span>
                    ${message(code: 'is.ui.actor.toolbar.new')}
                </span>
                <span class="end"></span>
        </a>
    </li>

    %{--Delete button--}%
    <li class="navigation-item button-ico button-delete">
        <a class="tool-button button-n on-selectable-disabled on-selectable-window-${controllerName}"
           onclick="jQuery.icescrum.selectableAction('actor/delete',null,null,function(data){ jQuery.event.trigger('remove_actor',[data]); jQuery.icescrum.renderNotice('${message(code:'is.actor.deleted')}'); });"
           data-is-shortcut
           data-is-shortcut-key="del"
           data-is-shortcut-on="#window-id-${controllerName}"
           alt="${message(code:'is.ui.actor.toolbar.alt.delete')}"
           title="${message(code:'is.ui.actor.toolbar.alt.delete')}">
                <span class="start"></span>
                <span class="content">
                    <span class="ico"></span>
                    ${message(code: 'is.ui.actor.toolbar.delete')}
                </span>
                <span class="end"></span>
        </a>
    </li>
</g:if>

%{--View--}%
<is:panelButton alt="View" id="menu-display" arrow="true" icon="view">
    <ul>
        <li class="first">
            <a href="${createLink(action:'list',controller:'actor',params:[product:params.product])}"
               data-default-view="postitsView"
               data-ajax-begin="$.icescrum.setDefaultView"
               data-ajax-update="#window-content-${controllerName}"
               data-ajax="true">${message(code:'is.view.postitsView')}</a>
        </li>
        <li class="last">
            <a href="${createLink(action:'list',controller:'actor',params:[product:params.product, viewType:'tableView'])}"
               data-default-view="tableView"
               data-ajax-begin="$.icescrum.setDefaultView"
               data-ajax-update="#window-content-${controllerName}"
               data-ajax="true">${message(code:'is.view.tableView')}</a>
        </li>
    </ul>
</is:panelButton>

%{--Export--}%
<is:panelButton alt="Export" id="menu-export" arrow="true" text="${message(code: 'is.ui.toolbar.export')}">
    <ul>
        <g:each in="${exportFormats}" var="format">
            <li>
                <div class="file-icon ${format.code.toLowerCase()}-format" style="display:inline-block">
                    <a data-ajax="true" href="${createLink(action:format.action?:'print',controller:format.controller?:controllerName,params:format.params)}">${format.name}</a>
                </div>
            </li>
        </g:each>
    </ul>
</is:panelButton>

%{--Search--}%

<entry:point id="${controllerName}-${actionName}"/>

<is:panelSearch id="search-ui">
    <is:autoCompleteSearch elementId="autoCmpTxt" controller="actor" action="list" update="window-content-${controllerName}" withTags="true"/>
</is:panelSearch>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="team-settings-modal" tabindex="-1" aria-hidden="true"
     class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center p-6 w-full md:inset-0 h-modal md:h-full">
    <div class="relative w-full max-w-2xl h-4/6">
        <div class="relative bg-white rounded-lg shadow h-full">
            <div class="flex justify-between items-start p-6 rounded-t">
                <h3 class="ml-7 flex-1 text-2xl font-medium text-gray-900 text-center">
                    Team Settings
                </h3>
                <button
                        type="button"
                        class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center"
                        data-modal-toggle="team-settings-modal">
                    <%@include file="/jsp/icons/close.jsp" %>
                </button>
            </div>
            <div class="p-6 pt-0 space-y-6">
                <div class="mb-4 border-b border-gray-200">
                    <ul class="flex flex-wrap -mb-px text-sm font-medium text-center" id="myTab"
                        data-tabs-toggle="#tab-content"
                        role="tablist">
                        <li class="mr-2" role="presentation">
                            <button class="inline-block p-4 rounded-t-lg border-b-2"
                                    id="join-requests-tab"
                                    data-tabs-target="#join-requests"
                                    type="button"
                                    role="tab"
                                    aria-controls="join-requests"
                                    aria-selected="false"
                            >Join Requests
                            </button>
                        </li>
                        <li class="mr-2" role="presentation">
                            <button class="inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300"
                                    id="team-experts-tab"
                                    data-tabs-target="#team-experts"
                                    type="button"
                                    role="tab"
                                    aria-controls="team-experts"
                                    aria-selected="false"
                            >Team Experts
                            </button>
                        </li>
                    </ul>
                </div>
                <div id="tab-content">
                    <div class="hidden rounded-lg"
                         id="join-requests"
                         role="tabpanel"
                         aria-labelledby="join-requests-tab">
                        <div id="join-requests-container" class="mb-3 overflow-y-auto max-h-[66%] border border-gray-200 rounded-lg shadow-sm">
                            <%@include file="/jsp/pages/user-team-chat/join-requests-table.jsp" %>
                        </div>
                    </div>
                    <div class="hidden rounded-lg"
                         id="team-experts"
                         role="team-experts"
                         aria-labelledby="team-experts-tab">
                        <div id="team-experts-container" class="overflow-y-auto max-h-[66%] border border-gray-200 rounded-lg shadow-sm">
                            <%@include file="/jsp/pages/user-team-chat/team-experts-table.jsp" %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.shared.notifications.domain.interactor

import com.android.systemui.shared.notifications.data.repository.NotificationSettingsRepository
import kotlinx.coroutines.flow.StateFlow

/** Encapsulates business logic for interacting with notification settings. */
class NotificationSettingsInteractor(
    private val repository: NotificationSettingsRepository,
) {
    val isNotificationHistoryEnabled = repository.isNotificationHistoryEnabled

    /** Should notifications be visible on the lockscreen? */
    suspend fun isShowNotificationsOnLockScreenEnabled(): StateFlow<Boolean> =
        repository.isShowNotificationsOnLockScreenEnabled()

    suspend fun setShowNotificationsOnLockscreenEnabled(enabled: Boolean) {
        repository.setShowNotificationsOnLockscreenEnabled(enabled)
    }

    /** Toggles the setting to show or hide notifications on the lock screen. */
    suspend fun toggleShowNotificationsOnLockscreenEnabled() {
        val current = repository.isShowNotificationsOnLockScreenEnabled().value
        repository.setShowNotificationsOnLockscreenEnabled(!current)
    }
}

package com.kadirbulut.spacexfan.common.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kadirbulut.spacexfan.R

class PermissionUtils(
    private val activity: AppCompatActivity,
    private val permissions: MutableCollection<String> = mutableListOf()
) {
    /***
     *  registerForActivityResult cannot set after being created.
     *  Fragments must call registerForActivityResult() before they are created (i.e. initialization, onAttach(), or onCreate()).
     *  That's why Permission helper should set in initialization, onAttach(), or onCreate().
     */
    private var mCurrentPermissionIndex = 0
    private var mIsAllSucceed = true
    var mOnCompleteListener: ((isAllSucceed: Boolean) -> Unit)? = null
    private val context: Context by lazy { activity.applicationContext }

    private val mPermissionResult = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { result ->
        if (result) {
            checkCompletion()
        } else {
            mIsAllSucceed = false
            val permission = permissions.elementAt(mCurrentPermissionIndex)
            if (isShowPermissionRationale(permission)) {
                showRationaleDialog()
            } else {
                checkCompletion()
            }
        }
    }

    private fun showRationaleDialog() {
        val dialog = MaterialAlertDialogBuilder(context)
            .setTitle(activity.getString(R.string.application_permission_permission_needed))
            .setMessage(activity.getString(R.string.application_permission_redirect_for_permission))
            .setPositiveButton(
                activity.getString(R.string.application_permission_allow)
            ) { dialog, i ->
                openAppSettings()
            }
            .setNegativeButton(
                activity.getString(R.string.application_permission_cancel)
            ) { dialog, i ->
                checkCompletion()
                getNextPermission()
            }
        dialog.show()
    }

    private fun checkCompletion() {
        if (isLastPermission()) {
            mOnCompleteListener?.invoke(mIsAllSucceed)
        }
    }

    fun onComplete(listener: ((isAllSucceed: Boolean) -> Unit)?): PermissionUtils {
        mOnCompleteListener = listener
        return this
    }

    private fun openAppSettings() {
        val i = Intent()
        i.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        i.addCategory(Intent.CATEGORY_DEFAULT)
        i.data = Uri.parse("package:" + context.packageName)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        activity.startActivity(i)
    }

    fun isShowPermissionRationale(permission: String) =
        !ActivityCompat.shouldShowRequestPermissionRationale(
            activity,
            permission
        )

    fun isPermissionGranted(permission: String) =
        ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED

    private fun getPermission(permission: String) {
        if (!isPermissionGranted(permission)) {
            mPermissionResult.launch(permission)
        } else {
            getNextPermission()
        }
    }

    private fun isLastPermission() = mCurrentPermissionIndex == permissions.size - 1

    fun getPermissions() {
        mCurrentPermissionIndex = 0
        getPermission(permissions.elementAt(mCurrentPermissionIndex))
    }

    private fun getNextPermission() {
        if (!isLastPermission()) {
            mCurrentPermissionIndex++
            getPermission(permissions.elementAt(mCurrentPermissionIndex))
        } else
            mOnCompleteListener?.invoke(mIsAllSucceed)
    }
}

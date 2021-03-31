package com.exae.proandroid.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

/**
 */

public abstract class CoreFragment extends Fragment {
//  protected TipDialog loadingDialog;
//  protected TipDialog tipDialog;
  private Gson gson;

  public CoreFragment() {
  }

  @ContentView
  public CoreFragment(@LayoutRes int contentLayoutId) {
    super(contentLayoutId);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gson = new Gson();
  }

  public void showLoading() {
//    if (loadingDialog != null) {
//      loadingDialog.show();
//    } else {
//      loadingDialog = crateLoadingDialog();
//      loadingDialog.show();
//    }
  }

//  public void dismissLoading() {
//    if (loadingDialog != null) loadingDialog.dismiss();
//  }

  public void toast(String msg) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
  }

//  private TipDialog crateLoadingDialog() {
//    return new TipDialog.Builder(requireActivity()).setIconType(TipDialog.Builder.ICON_TYPE_LOADING)
//        .setTipWord(getString(R.string.loading))
//        .create();
//  }

//  private TipDialog createTipDialog(String msg, int iconType) {
//    return new TipDialog.Builder(requireActivity())
//        .setIconType(iconType)
//        .setTipWord(msg)
//        .create();
//  }



  /**
   * 带图标的提示框
   */
//  public void showTip(String message, int iconType) {
//    if (tipDialog == null) {
//      tipDialog = createTipDialog(message, iconType);
//      tipDialog.setCanceledOnTouchOutside(true);
//    }
//    if (!tipDialog.isShowing()) {
//      tipDialog.show();
//    }
//  }
//
//  public void showNetError(Resource repos) {
//    if (repos.isFail()) {
//      toast(repos.getMessage());
//    } else {
//      toast(formatError(repos));
//    }
//  }


}

// Generated code from Butter Knife. Do not modify!
package com.jldroid25.android.boutique;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BoutiqueEditorActivity_ViewBinding<T extends BoutiqueEditorActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BoutiqueEditorActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.productNameEditText = Utils.findRequiredViewAsType(source, R.id.edit_product_name, "field 'productNameEditText'", EditText.class);
    target.productPriceEditText = Utils.findRequiredViewAsType(source, R.id.edit_product_price, "field 'productPriceEditText'", EditText.class);
    target.productQuantityEditText = Utils.findRequiredViewAsType(source, R.id.edit_product_quantity, "field 'productQuantityEditText'", EditText.class);
    target.supplierName = Utils.findRequiredViewAsType(source, R.id.edit_supplier_name, "field 'supplierName'", EditText.class);
    target.supplierPhone = Utils.findRequiredViewAsType(source, R.id.edit_supplier_phone, "field 'supplierPhone'", EditText.class);
    target.orderBtn = Utils.findRequiredViewAsType(source, R.id.order_btn, "field 'orderBtn'", Button.class);
    target.plusBtn = Utils.findRequiredViewAsType(source, R.id.qty_plus_btn, "field 'plusBtn'", Button.class);
    target.minusBtn = Utils.findRequiredViewAsType(source, R.id.qty_minus_btn, "field 'minusBtn'", Button.class);
    target.deleteButton = Utils.findRequiredViewAsType(source, R.id.del_btn, "field 'deleteButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.productNameEditText = null;
    target.productPriceEditText = null;
    target.productQuantityEditText = null;
    target.supplierName = null;
    target.supplierPhone = null;
    target.orderBtn = null;
    target.plusBtn = null;
    target.minusBtn = null;
    target.deleteButton = null;

    this.target = null;
  }
}

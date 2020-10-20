package personal.mine.bse;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class FaqAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataFaq;
    private HashMap<String,List<String>> listHashMap;

    public FaqAdapter(Context context, List<String> listDataFaq, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listDataFaq = listDataFaq;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataFaq.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataFaq.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataFaq.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataFaq.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String)getGroup(groupPosition);
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_pertanyaan,null);
        }
        TextView tv_listtanya = (TextView)convertView.findViewById(R.id.tv_listtanya);
        tv_listtanya.setTypeface(null, Typeface.BOLD);
        tv_listtanya.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String)getChild(groupPosition, childPosition);

        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_jawaban, null);
        }

        TextView tv_listjawab = (TextView)convertView.findViewById(R.id.tv_listjawab);
        tv_listjawab.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

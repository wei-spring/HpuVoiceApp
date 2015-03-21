package com.hpuvoice.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hpuvoice.adapters.MessageAdapter;
import com.hpuvoice.adapters.SpinnerAdapter;
import com.hpuvoice.app.R;
import com.hpuvoice.fragments.dummy.DummyContent;
import com.hpuvoice.modles.Message;
import com.hpuvoice.ui.chat.MessageInputToolBox;
import com.hpuvoice.ui.chat.OnOperationListener;
import com.hpuvoice.ui.chat.Option;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ShowFragment extends Fragment implements AbsListView.OnItemClickListener {

    private int[] itemLogo = {R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher};
    private List<String> itemName;
    private OnFragmentInteractionListener mListener;
    private Spinner choose_tlking_object;
    private SpinnerAdapter mTalkObjectAdapter;
    private MessageInputToolBox messageInputToolBox;
    private ListView messageListview;
    private MessageAdapter msgAdapter;

    public ShowFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemName = new ArrayList<String>();
        itemName.add("聊天");
        itemName.add("笑话");
        itemName.add("图片");
        itemName.add("天气");
        itemName.add("问答");
        itemName.add("百科");
        itemName.add("故事");
        mTalkObjectAdapter = new SpinnerAdapter(itemLogo, itemName, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        choose_tlking_object = (Spinner) view.findViewById(R.id.choose_tlking_object);
        choose_tlking_object.setAdapter(mTalkObjectAdapter);
        choose_tlking_object.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "你点击的是:" + itemName.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        messageInputToolBox = (MessageInputToolBox) view.findViewById(R.id.messageInputToolBox);
        messageListview = (ListView) view.findViewById(R.id.messageListview);
        initMessageInputToolBox();
        initListView();
        return view;
    }

    private void initMessageInputToolBox() {

        messageInputToolBox.setOnOperationListener(new OnOperationListener() {

            @Override
            public void send(String content) {

                System.out.println("===============" + content);

                Message message = new Message(0, 1, "Tom", "avatar", "Jerry", "avatar", content, true, true, new Date());

                msgAdapter.getData().add(message);
                messageListview.setSelection(messageListview.getBottom());

                //Just demo
                createReplayMsg(message);
            }

            @Override
            public void selectedFace(String content) {

                System.out.println("===============" + content);
                Message message = new Message(Message.MSG_TYPE_FACE, Message.MSG_STATE_SUCCESS, "Tomcat", "avatar", "Jerry", "avatar", content, true, true, new Date());
                msgAdapter.getData().add(message);
                messageListview.setSelection(messageListview.getBottom());

                //Just demo
                createReplayMsg(message);
            }


            @Override
            public void selectedFuncation(int index) {

                System.out.println("===============" + index);

                switch (index) {
                    case 0:
                        //do some thing
                        break;
                    case 1:
                        //do some thing
                        break;

                    default:
                        break;
                }
                Toast.makeText(getActivity(), "Do some thing here, index :" + index, Toast.LENGTH_SHORT).show();

            }

        });

        ArrayList<String> faceNameList = new ArrayList<String>();
        for (int x = 1; x <= 10; x++) {
            faceNameList.add("big" + x);
        }

        ArrayList<String> faceNameList1 = new ArrayList<String>();
        for (int x = 1; x <= 7; x++) {
            faceNameList1.add("cig" + x);
        }

        ArrayList<String> faceNameList2 = new ArrayList<String>();
        for (int x = 1; x <= 24; x++) {
            faceNameList2.add("dig" + x);
        }

        Map<Integer, ArrayList<String>> faceData = new HashMap<Integer, ArrayList<String>>();
        faceData.put(R.drawable.em_cate_magic, faceNameList2);
        faceData.put(R.drawable.em_cate_rib, faceNameList1);
        faceData.put(R.drawable.em_cate_duck, faceNameList);
        messageInputToolBox.setFaceData(faceData);


        List<Option> functionData = new ArrayList<Option>();
        for (int x = 0; x < 5; x++) {
            Option takePhotoOption = new Option(getActivity(), "Take", R.drawable.take_photo);
            Option galleryOption = new Option(getActivity(), "Gallery", R.drawable.gallery);
            functionData.add(galleryOption);
            functionData.add(takePhotoOption);
        }
        messageInputToolBox.setFunctionData(functionData);
    }

    private void initListView() {


        //create Data
        Message message = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "Hi", false, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8));
        Message message1 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "Hello World", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8));
        Message message2 = new Message(Message.MSG_TYPE_PHOTO, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "device_2014_08_21_215311", false, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 7));
        Message message3 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "Haha", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 7));
        Message message4 = new Message(Message.MSG_TYPE_FACE, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "big3", false, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 7));
        Message message5 = new Message(Message.MSG_TYPE_FACE, Message.MSG_STATE_SUCCESS, "Tom", "avatar", "Jerry", "avatar", "big2", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6));
        Message message6 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_FAIL, "Tom", "avatar", "Jerry", "avatar", "test send fail", true, false, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6));
        Message message7 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SENDING, "Tom", "avatar", "Jerry", "avatar", "test sending", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6));

        List<Message> messages = new ArrayList<Message>();
        messages.add(message);
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
        messages.add(message5);
        messages.add(message6);
        messages.add(message7);

        msgAdapter = new MessageAdapter(getActivity(), messages);
        messageListview.setAdapter(msgAdapter);
        msgAdapter.notifyDataSetChanged();

        messageListview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                messageInputToolBox.hide();
                return false;
            }
        });

    }

    private void createReplayMsg(Message message) {

        final Message reMessage = new Message(message.getType(), 1, "Tom", "avatar", "Jerry", "avatar",
                message.getType() == 0 ? "Re:" + message.getContent() : message.getContent(),
                false, true, new Date()
        );
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * (new Random().nextInt(3) + 1));
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            msgAdapter.getData().add(reMessage);
                            messageListview.setSelection(messageListview.getBottom());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

}
